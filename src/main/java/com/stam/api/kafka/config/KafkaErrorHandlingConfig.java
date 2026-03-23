package com.stam.api.kafka.config;

import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaErrorHandlingConfig {

    @Bean
    DeadLetterPublishingRecoverer dltRecoverer(
            KafkaTemplate<String, String> kafkaTemplate
    ) {
        return new DeadLetterPublishingRecoverer(
                kafkaTemplate,
                (record, ex) -> new TopicPartition(record.topic() + ".dlt", record.partition())
        );
    }

    @Bean
    DefaultErrorHandler kafkaErrorHandler(DeadLetterPublishingRecoverer dltRecoverer) {
        return new DefaultErrorHandler(dltRecoverer, new FixedBackOff(0L, 0L));
    }
}
