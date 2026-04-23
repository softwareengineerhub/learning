package com.app.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String key, String value) {
        System.out.println(String.format("Sending msg: key=%s; value=%s", key, value));
        kafkaTemplate.send("my-topic", key, value);
    }

}
