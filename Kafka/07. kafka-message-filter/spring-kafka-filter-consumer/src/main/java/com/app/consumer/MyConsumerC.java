package com.app.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MyConsumerC {

    @KafkaListener(topics = "t_topic", groupId = "consumerc", containerFactory = "farLocationContainerFactory")
    public void consume(String message){
        System.out.println("consumerc: "+hashCode()+" ;message="+message);
    }
}
