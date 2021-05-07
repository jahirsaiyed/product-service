package com.example.demo.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository("ProductSearchRepositoryJPA")
public interface ProductSearchRepository extends ElasticsearchRepository<Product, Long> {
}
