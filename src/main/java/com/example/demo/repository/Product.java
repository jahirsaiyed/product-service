package com.example.demo.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

import static org.springframework.data.elasticsearch.annotations.FieldType.Nested;

@Document(indexName = "products-master-idx")
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

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public ProductHardware getHardware() {
        return hardware;
    }

    public void setHardware(ProductHardware hardware) {
        this.hardware = hardware;
    }

    public ProductRelease getRelease() {
        return release;
    }

    public void setRelease(ProductRelease release) {
        this.release = release;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", brand='").append(brand).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", picture='").append(picture).append('\'');
        sb.append(", sim='").append(sim).append('\'');
        sb.append(", resolution='").append(resolution).append('\'');
        sb.append(", hardware=").append(hardware);
        sb.append(", release=").append(release);
        sb.append('}');
        return sb.toString();
    }
}
