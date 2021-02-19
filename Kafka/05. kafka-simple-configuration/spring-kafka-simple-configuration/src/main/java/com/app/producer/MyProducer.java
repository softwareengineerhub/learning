package com.app.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private int i;

    @Scheduled(fixedRate = 1000L)
    public void sendMessage(){
        i++;
        send("Counter is: "+i);
    }

    public void send(String message){
        System.out.println("Sending msg: "+message);
        kafkaTemplate.send("t_topic", message);
    }

}
