package com.app.consumer;

import jakarta.annotation.PreDestroy;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MyConsumer {

    //@KafkaListener(topics = "my-topic", groupId = "default-spring-consumer", containerFactory = "kafkaListenerContainerFactory")
    //@KafkaListener(topics = "my-topic", groupId = "default-spring-consumer")
    @KafkaListener(topics = "DenysTest")
    public void onMessage(ConsumerRecord<String, String> message) {
        System.out.println("@In Consumer");
        System.out.println("Consumed: " + message);
    }

    @PreDestroy
    public void destroy(){
        System.out.println("MyConsumer.@PreDestroy.start");
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("MyConsumer.@PreDestroy.finish");
    }
}
