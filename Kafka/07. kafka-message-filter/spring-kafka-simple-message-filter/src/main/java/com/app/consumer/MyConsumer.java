package com.app.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyConsumer {

  //  @KafkaListener(topics = "t_topic")
    public void consume(String message){
        if(message.startsWith("a")){
            return;
        }
        System.out.println(hashCode()+" ;message="+message);
    }
}
