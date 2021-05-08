package com.example.demo.service;

import com.example.demo.service.dto.ProductDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessagingService {
    private final KafkaTemplate kafkaTemplate;
    private final ObjectMapper objectMapper;
    @Value("${PRODUCT_EVENTS_TOPIC}")
    private String productEventTopic;

    public void send(ProductDTO product) {
        try {
            kafkaTemplate.send(new ProducerRecord<>(productEventTopic, objectMapper.writeValueAsString(product)));
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Invalid product");
        }
    }

}
