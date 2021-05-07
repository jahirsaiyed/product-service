package com.example.demo.service.dto;

public class ProductSearchDTO {
    private Long id;
    private String brand;
    private String phone;
    private String picture;
    private String sim;
    private String resolution;
    private ProductHardwareDTO hardware;
    private ProductReleaseDTO release;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public ProductHardwareDTO getHardware() {
        return hardware;
    }

    public void setHardware(ProductHardwareDTO hardware) {
        this.hardware = hardware;
    }

    public ProductReleaseDTO getRelease() {
        return release;
    }

    public void setRelease(ProductReleaseDTO release) {
        this.release = release;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductSearchDTO{");
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
