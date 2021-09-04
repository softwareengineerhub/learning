package com.app.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MyResultTopicListenerForCustom {

    @KafkaListener(topics = "t.commodity.promotion-uppercase-custom2", groupId = "mytest")
    public void consume(ConsumerRecord<String, String> record){
        String message = record.value();
        System.out.println("MyResultTopicListenerForCustom: "+message);
    }

}
