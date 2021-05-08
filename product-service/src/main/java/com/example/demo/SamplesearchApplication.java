package com.example.demo;

import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ProductSearchRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableElasticsearchRepositories(basePackageClasses = ProductSearchRepository.class,
		excludeFilters=@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ProductRepository.class))
@EnableMongoRepositories(basePackageClasses = ProductRepository.class,
		excludeFilters=@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ProductSearchRepository.class))
public class SamplesearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SamplesearchApplication.class, args);
	}

}
