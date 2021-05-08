package com.example.demo.utils;

import com.example.demo.repository.Product;
import com.example.demo.service.dto.ProductDTO;

public class ProductUtils {

    public static Product copy(Product product) {
        Product p = new Product();
        p.setId(product.getId());
        p.setPhone(product.getPhone());
        p.setBrand(product.getBrand());
        return p;
    }

    public static ProductDTO copy(ProductDTO product) {
        ProductDTO p = new ProductDTO();
        p.setId(product.getId());
        p.setPhone(product.getPhone());
        p.setBrand(product.getBrand());
        return p;
    }
}
