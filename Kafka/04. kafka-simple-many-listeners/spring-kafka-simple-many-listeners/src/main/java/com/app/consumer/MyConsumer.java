package com.app.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyConsumer {

    @KafkaListener(topics = "t_topicpool", concurrency = "4")
    public void consume(String message){
        System.out.println(hashCode()+"CONSUME().start"+message+"; thread="+Thread.currentThread().getId());
        delay(10000);
        System.out.println(hashCode()+" ;message="+message);
        delay(10000);
        System.out.println(hashCode()+"CONSUME().finish"+message+"; thread="+Thread.currentThread().getId());
    }


    private void delay(long period){
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis()-start<=period){
        }
    }
}
