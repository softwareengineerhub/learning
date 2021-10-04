package com.app.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MyCountListener1 {

    @KafkaListener(topics = "t.commodity.flashsale.vote-one-result", groupId = "mytest")
    public void consume(ConsumerRecord<String, String> record){
        String message = record.value();
        System.out.println("MyCountListener1: "+message);
    }

}
