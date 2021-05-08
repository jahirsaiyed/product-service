package com.example.demo.service.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String id;
    private String brand;
    private String phone;
    private String picture;
    private String sim;
    private String resolution;
    private ProductHardwareDTO hardware;
    private ProductReleaseDTO release;
}
