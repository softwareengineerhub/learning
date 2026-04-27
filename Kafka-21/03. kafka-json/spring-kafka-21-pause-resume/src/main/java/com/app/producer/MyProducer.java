package com.app.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String name) {
        System.out.println(String.format("Sending msg: %s", name));
        kafkaTemplate.send("exampleTopicApp", name);
    }

}
