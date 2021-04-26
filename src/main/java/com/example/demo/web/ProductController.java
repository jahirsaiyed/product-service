package com.example.demo.web;

import com.example.demo.repository.ProductSearchRepositoryV2;
import com.example.demo.service.ProductService;
import com.example.demo.service.mapper.ProductMapper;
import com.example.demo.web.request.ProfileSearchRequest;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSearchRepositoryV2 productSearchRepositoryV2;

    private ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @PostMapping
    private ResponseEntity<?> searchProduct(@RequestBody ProfileSearchRequest request) {
        return new ResponseEntity<>(productService.search(mapper.map(request)), HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<?> search() {
        return new ResponseEntity<>(productSearchRepositoryV2.findAll(), HttpStatus.OK);
    }
}
