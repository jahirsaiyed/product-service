package com.example.demo.web;

import com.example.demo.repository.Product;
import com.example.demo.repository.ProductSearchRepositoryV2;
import com.example.demo.service.ProductService;
import com.example.demo.service.mapper.ProductMapper;
import com.example.demo.web.request.ProfileSearchRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${elasticsearch.httpUrls}")
    private String elasticSearchUrls;

    @Autowired
    private ProductSearchRepositoryV2 productSearchRepositoryV2;

    private ProductMapper mapper = Mappers.getMapper(ProductMapper.class);
    @Autowired
    RestTemplate restTemplate;

    @PostConstruct
    public void initialLoad() {
        new Thread(() -> {
            try {
                Thread.sleep(10000);
                addProducts();
            } catch (Exception e) {}
        }).start();
    }

    @PostMapping("/load")
    private ResponseEntity<?> addProducts() throws IOException {
        //TODO: The load products can be improved to load the products through kafka elasticsearch-sink connector.
        createIndex();
        postDocuments();
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Search for product", notes = "This uri will search for products")
    private ResponseEntity<?> searchProduct(@RequestBody ProfileSearchRequest request) {
        return new ResponseEntity<>(productService.search(mapper.map(request)), HttpStatus.OK);
    }

    private void postDocuments() throws IOException {
        File resource = new ClassPathResource("sample_products.json").getFile();
        List<Product> products = Arrays.asList(objectMapper.readValue(resource, Product[].class));
        for (Product product : products) {
            HttpEntity<Product> request = new HttpEntity<Product>(product);
            String response = restTemplate.postForObject(elasticSearchUrls + "/products-master-idx/_doc/"+product.getId(), request, String.class);
            System.out.println(response);
        }
    }

    private void createIndex() {
        try {
            File indexDefinition = new ClassPathResource("product-master-idx.json").getFile();
            Map index = objectMapper.readValue(indexDefinition, Map.class);
            restTemplate.put(elasticSearchUrls + "/products-master-idx", index, String.class);
        } catch (Exception e) {
            System.out.println("Index already present..");
        }
    }

    //    @GetMapping
//    private ResponseEntity<?> search() {
//        return new ResponseEntity<>(productSearchRepositoryV2.findAll(), HttpStatus.OK);
//    }
}
