package com.app.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MyAnotherConsumer {


    @KafkaListener(topics = "t_topicpool2", concurrency = "1")
    public void consume(ConsumerRecord<String, String> record){
        String message = record.value();
        System.out.println("MyAnotherConsumer:"+hashCode()+"CONSUME().start"+message+"; thread="+Thread.currentThread().getId()+"; PARTITiON="+record.partition());
    }
}
