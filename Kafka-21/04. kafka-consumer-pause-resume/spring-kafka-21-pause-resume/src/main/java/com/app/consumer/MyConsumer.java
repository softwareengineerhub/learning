package com.app.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MyConsumer {

    @KafkaListener(topics = "exampleTopicApp")
    public void onMessage(String value) {
        System.out.println("----------Consumed------");
        System.out.println("Consumed: " + value);
    }
}
