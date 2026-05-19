package com.app.producer;

import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String message){
        System.out.println("Sending msg: "+message);
        kafkaTemplate.send("DenysTest", message);
    }

    @PreDestroy
    public void destroy(){
        System.out.println("MyProducer.@PreDestroy.start");
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("MyProducer.@PreDestroy.finish");
    }

}
