package com.example.demo.repository;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

import static org.springframework.data.elasticsearch.annotations.FieldType.Nested;

@Document(indexName = "products-master-idx")
@org.springframework.data.mongodb.core.mapping.Document("product")
@Data
public class Product implements Serializable {
    @Id
    private String id;
    private String brand;
    private String phone;
    private String picture;
    private String sim;
    private String resolution;
    @Field(type = Nested, includeInParent = true)
    private ProductHardware hardware;
    @Field(type = Nested, includeInParent = true)
    private ProductRelease release;
}
