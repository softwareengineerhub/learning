package com.app.listener.order;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MyFilterLargeListener {

    @KafkaListener(topics = "t.commodity.order-one2", groupId = "mytest")
    public void consume(ConsumerRecord<String, String> record){
        String message = record.value();
        System.out.println("MyFilterLargeListener: "+message+"; key"+record.key());
    }

}
