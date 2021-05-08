package com.example.demo.repository;

import com.example.demo.service.dto.ProductSearchRequest;
import com.samskivert.mustache.Template;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class ProductJestClient {

    @Value("${product.index}")
    private String indexName;

    private JestClient client;
    private Template productSearchTemplate;

    @Autowired
    public ProductJestClient(JestClient client, Template productSearchTemplate) {
        this.client = client;
        this.productSearchTemplate = productSearchTemplate;
    }

    public ProductResult search(ProductSearchRequest productSearchRequest) {
        String query = productSearchTemplate.execute(productSearchRequest);
        Search.Builder searchBuilder = new Search.Builder(query).addIndex(indexName);
        SearchResult result = null;

        try {
            result = client.execute(searchBuilder.build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<SearchResult.Hit<Product, Void>> hits;

        if (result == null) return new ProductResult();
        System.out.println(result.getErrorMessage());
        if (result.getErrorMessage() != null && !result.getErrorMessage().equalsIgnoreCase("")) {
            throw new RuntimeException("Source system returned error message.");
        }

        try {
            hits = result.getHits(Product.class);
        } catch (Exception e) {
            return new ProductResult();
        }

        List<Product> products = hits.stream()
                .filter(Objects::nonNull)
                .map(h -> h.source)
                .collect(Collectors.toList());


        if (CollectionUtils.isEmpty(hits)) return new ProductResult();

        ProductResult searchResult = new ProductResult(result.getMaxScore(), result.getJsonObject().get("took").getAsString(), hits.size(), products);
        return searchResult;
    }
}
