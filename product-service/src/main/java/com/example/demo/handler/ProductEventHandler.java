package com.example.demo.handler;

import com.example.demo.repository.Product;
import com.example.demo.repository.ProductSearchRepository;
import com.example.demo.service.dto.ProductDTO;
import com.example.demo.service.mapper.ProductMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductEventHandler {

    private final Logger log = LoggerFactory.getLogger(ProductEventHandler.class);

    private final ObjectMapper mapper;
    private final ProductMapper productMapper;
    private final ProductSearchRepository productSearchRepository;

    @KafkaListener(topics = "${PRODUCT_EVENTS_TOPIC}", groupId = "${PRODUCT_EVENTS_GROUP}")
    public void handle(@Payload String eventMessage) {
        try {
            ProductDTO productDTO = mapper.readValue(eventMessage, ProductDTO.class);
            Product product = productMapper.toEntity(productDTO);
            productSearchRepository.save(product);
        } catch(Exception e) {
            log.error("Error while saving to elastic search", e);
        }
    }
}
