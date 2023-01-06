package com.mishinyura.booksmaven.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    @KafkaListener(topics = "books", groupId = "group_id")
    public void consumeMessage(String message) {
        System.out.println(message);
    }
}
