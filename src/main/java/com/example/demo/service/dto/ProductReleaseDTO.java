package com.example.demo.service.dto;

public class ProductReleaseDTO {
    private String announceDate;
    private Double priceEur;

    public String getAnnounceDate() {
        return announceDate;
    }

    public void setAnnounceDate(String announceDate) {
        this.announceDate = announceDate;
    }

    public Double getPriceEur() {
        return priceEur;
    }

    public void setPriceEur(Double priceEur) {
        this.priceEur = priceEur;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductRelease{");
        sb.append("announceDate='").append(announceDate).append('\'');
        sb.append(", priceEur=").append(priceEur);
        sb.append('}');
        return sb.toString();
    }
}
