package com.app.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MyListener2 {

    @KafkaListener(topics = "t.commodity.feedback-four-bad", groupId = "mytest")
    public void consume(ConsumerRecord<String, Object> record){
        Object message = record.value();
        System.out.println("MyListener2: "+message);
    }

}
