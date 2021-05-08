package com.example.demo.service;

import com.example.demo.service.dto.ProductDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductSearchResultDTO {
    private float maxScore;
    private long totalCount;
    private String latency;
    private String queryLatency;
    private List<ProductDTO> products = new ArrayList<>();
}
