package com.stam.api.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stam.api.dto.GameRequestDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Publie des messages d'import catalogue sur Kafka.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CatalogImportProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    @Value("${stam.kafka.catalog-import-topic:stam.catalog.import}")
    private String topic;

    public int publishBatch(List<GameRequestDTO> games) {
        if (games == null || games.isEmpty()) {
            return 0;
        }
        games.forEach(this::publishOne);
        return games.size();
    }

    public void publishOne(GameRequestDTO dto) {
        String payload;
        try {
            payload = objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            log.warn("Impossible de sérialiser le GameRequestDTO pour Kafka: {}", e.getMessage());
            return;
        }

        kafkaTemplate.send(topic, payload);
        log.info("Message publié (topic={})", topic);
    }
}
