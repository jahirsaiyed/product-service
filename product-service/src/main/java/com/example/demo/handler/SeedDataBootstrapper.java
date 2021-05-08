package com.example.demo.handler;

import com.example.demo.repository.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.dto.ProductDTO;
import com.example.demo.service.mapper.ProductMapper;
import com.example.demo.web.ProductController;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SeedDataBootstrapper {

    private final Logger log = LoggerFactory.getLogger(SeedDataBootstrapper.class);

    private final ObjectMapper objectMapper;
    @Value("${elasticsearch.httpUrls}")
    private String elasticSearchUrls;
    private final RestTemplate restTemplate;
    private final ProductService productService;
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @PostConstruct
    public void initialLoad() {
        new Thread(() -> {
            try {
                Thread.sleep(10000);
                addProducts();
            } catch (Exception e) {
                log.error("Error while creating seed data", e);
            }
        }).start();
    }

    private ResponseEntity<?> addProducts() throws IOException {
        //TODO: This method can be improved to load the products through kafka elasticsearch-sink connector.
        createIndex();
        postDocuments();
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }

    private void postDocuments() throws IOException {
        File resource = new ClassPathResource("sample_products.json").getFile();
        List<Product> products = Arrays.asList(objectMapper.readValue(resource, Product[].class));
        for (Product product : products) {
            ProductDTO savedProduct = saveProduct(product);
            log.info(String.valueOf(savedProduct));
        }
    }

    private void createIndex() {
        try {
            File indexDefinition = new ClassPathResource("product-master-idx.json").getFile();
            Map index = objectMapper.readValue(indexDefinition, Map.class);
            restTemplate.put(elasticSearchUrls + "/products-master-idx", index, String.class);
        } catch (Exception e) {
            log.warn("Index already present..");
        }
    }

    private ProductDTO saveProduct(Product product) {
        return productService.save(productMapper.toDto(product));
//            HttpEntity<Product> request = new HttpEntity<Product>(product);
//            String response = restTemplate.postForObject(elasticSearchUrls + "/products-master-idx/_doc/" + product.getId(), request, String.class);
    }

}
