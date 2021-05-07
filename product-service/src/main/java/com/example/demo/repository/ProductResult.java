package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

public class ProductResult {
    private float maxScore;
    private long totalCount;
    private String latency;
    private String queryLatency;
    private List<Product> products = new ArrayList<>();

    public ProductResult() {
    }

    public ProductResult(float maxScore, String queryLatency, long totalCount, List<Product> products) {
        this.maxScore = maxScore;
        this.totalCount = totalCount;
        this.queryLatency = queryLatency;
        this.products = products;
    }

    public float getMaxScore() {
        return maxScore;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public String getLatency() {
        return latency;
    }

    public String getQueryLatency() {
        return queryLatency;
    }

    public List<Product> getProducts() {
        return products;
    }
}
