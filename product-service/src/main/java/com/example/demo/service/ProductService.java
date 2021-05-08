package com.example.demo.service;

import com.example.demo.service.dto.ProductDTO;
import com.example.demo.service.dto.ProductSearchRequest;

import java.util.Optional;

public interface ProductService {
    ProductSearchResultDTO search(ProductSearchRequest productSearchRequest);

    ProductDTO save(ProductDTO productDTO);

    Optional<ProductDTO> partialUpdate(ProductDTO productDTO);

    Optional<ProductDTO> findById(String id);
}
