package com.app.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MyConsumerB {

    //, containerFactory = "farLocationContainerFactory"

   // @KafkaListener(topics = "t_topic", groupId = "consumerb")
    public void consume(String message){
        if(message.startsWith("a")){
            return;
        }
        System.out.println(hashCode()+" ;message="+message);
    }
}
