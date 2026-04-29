package ru.tbank.practicum.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaListenerExample {

    @KafkaListener(topics = "test-topic", groupId = "group1")
    void listener(String data) {
        log.info("Received message [{}] in group1", data);
    }
}