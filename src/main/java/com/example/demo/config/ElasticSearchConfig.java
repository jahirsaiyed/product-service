package com.example.demo.config;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mustache.MustacheProperties;
import org.springframework.boot.autoconfigure.mustache.MustacheResourceTemplateLoader;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Reader;
import java.util.Arrays;

@Configuration
@EnableConfigurationProperties({MustacheProperties.class})
public class ElasticSearchConfig {

    @Value("${elasticsearch.httpUrls}")
    private String elasticSearchUrls;
    @Value("${jest.conn.timeout}")
    private int jestConnTimeout;
    @Value("${jest.read.timeout}")
    private int jestReadTimeout;

    @Autowired
    MustacheResourceTemplateLoader loader;

    @Bean
    public JestClient jestClient() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(
                new HttpClientConfig.Builder(Arrays.asList(elasticSearchUrls.split(",")))
                        .multiThreaded(true)
                        .connTimeout(jestConnTimeout)
                        .readTimeout(jestReadTimeout)
                        .build());

        JestClient client = factory.getObject();
        return client;
    }

    @Bean
    public Template profileSearchTemplate() throws Exception {
        Reader reader = loader.getTemplate("productSearch");
        Template template = Mustache.compiler().defaultValue("").compile(reader);
        return template;

    }

}
