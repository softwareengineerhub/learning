package com.app.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MyPersonRewardsListener2 {

    @KafkaListener(topics = "t.commodity.reward-one2", groupId = "mytest")
    public void consume(ConsumerRecord<String, String> record){
        String message = record.value();
        System.out.println("MyPersonRewardsListener2: "+message);
    }

}
