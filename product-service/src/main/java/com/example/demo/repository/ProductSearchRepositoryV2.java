//package com.example.demo.repository;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.annotations.Query;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//import org.springframework.stereotype.Repository;
//
////@Repository("ProductSearchRepositoryQuery")
//public interface ProductSearchRepositoryV2  extends ElasticsearchRepository<Product, Long> {
//
//    @Query("{\"bool\": {\"must\": [{\"match\": {\"release.announceDate\": \"?0\"}}]}}")
//    Page<Product> findByAnnounceDate(String date, Pageable pageable);
//
//    @Query("{\"bool\": {\"must\": [{\"match\": {\"sim\": \"?0\"}}]}}")
//    Page<Product> findBySim(String sim, Pageable pageable);
//
//    @Query("{\"bool\": {\"must\": [{\"match\": {\"sim\": \"?0\"}}]}}")
//    Page<Product> findBySimAndAnnounceDate(String sim, Pageable pageable);
//
//}
