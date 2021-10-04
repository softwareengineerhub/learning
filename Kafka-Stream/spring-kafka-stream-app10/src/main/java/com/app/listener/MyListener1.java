package com.app.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MyListener1 {

    @KafkaListener(topics = "t.commodity.person2", groupId = "mytest")
    public void consume(ConsumerRecord<String, Object> record){
        Object message = record.value();
        System.out.println("MyListener1: "+message);
    }

}
