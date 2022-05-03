package com.app.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MyConsumer {
    @Autowired
    private LagComponent lagComponent;

    @KafkaListener(topics = "my-topic", groupId = "test-group", id = "test")
    public void onMessage(String message) {
        try {
           // Thread.sleep(8000);
            System.out.println("Consumed: " + message);
            System.out.println("Here");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
