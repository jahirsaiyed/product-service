package com.example.demo.service;

import com.example.demo.service.dto.ProductSearchDTO;
import com.example.demo.service.dto.ProductSearchRequest;

import java.util.List;

public interface ProductService {
    List<ProductSearchDTO> search(ProductSearchRequest productSearchRequest);
}
