package com.app.listener.order;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MyNonPlasticListener {

    @KafkaListener(topics = "t.commodity.nonplastic", groupId = "mytest")
    public void consume(ConsumerRecord<String, String> record){
        String message = record.value();
        System.out.println("MyNonPlasticListener: "+message);
    }

}
