package com.app.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MyConsumerB {

    @KafkaListener(topics = "t_topic", groupId = "test")
    public void consume(String message){
        if(message.startsWith("q")){
          //  String s = null;
          //  s.toString();
            throw new RuntimeException("Incorrect message");
        }
        System.out.println("!!!!!"+hashCode()+" ;message="+message);
    }
}
