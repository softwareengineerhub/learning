package com.app.listener;

import com.app.model.PersonPattern;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MyPersonPatternListener1 {

    @KafkaListener(topics = "t.commodity.person-one2", groupId = "mytest")
    public void consume(ConsumerRecord<String, String> record){
        String message = record.value();
        System.out.println("MyPersonPatternListener1: "+message);
    }

}
