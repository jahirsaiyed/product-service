package com.example.demo.service.dto;

import lombok.Data;

@Data
public class ProductSearchRequest {
    private Double priceEur;
    private String sim;
    private String announceDate;
}
