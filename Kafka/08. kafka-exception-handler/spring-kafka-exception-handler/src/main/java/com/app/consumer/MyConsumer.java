package com.app.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MyConsumer {

    @KafkaListener(topics = "t_topic", groupId = "test", errorHandler = "myErrorHandler")
    public void consume(String message){
        if(message.startsWith("q")){
            throw new RuntimeException("Incorrect message");
        }
        System.out.println("!!!!!"+hashCode()+" ;message="+message);
    }
}
