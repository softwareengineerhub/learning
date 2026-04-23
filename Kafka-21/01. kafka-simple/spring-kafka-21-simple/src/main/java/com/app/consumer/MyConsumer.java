package com.app.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MyConsumer {

    //@KafkaListener(topics = "my-topic", groupId = "default-spring-consumer", containerFactory = "kafkaListenerContainerFactory")
    //@KafkaListener(topics = "my-topic", groupId = "default-spring-consumer")
    @KafkaListener(topics = "my-topic" )
    public void onMessage(String message) {
        System.out.println("Consumed: " + message);
    }
}
