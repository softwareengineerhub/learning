package com.app.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MyResultTopicListener {

    @KafkaListener(topics = "t.commodity.promotion-uppercase", groupId = "mytest")
    public void consume(ConsumerRecord<String, String> record){
        String message = record.value();
        System.out.println("ResultTopicListener: "+message);
    }

}
