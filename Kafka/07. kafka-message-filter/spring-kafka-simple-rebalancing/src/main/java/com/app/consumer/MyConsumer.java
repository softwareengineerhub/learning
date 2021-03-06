package com.app.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyConsumer {

    @KafkaListener(topics = "t_rebalance", concurrency = "3")
    public void consume(ConsumerRecord<String, String> message){
        int partition = message.partition();
        long offset = message.offset();
        String value = message.value();
        String msg = String.format("partition=%s; offset=%s; value=%s; thread=%s", partition, offset, value, Thread.currentThread().getId());
        System.out.println(msg);
    }

    private void delay(long period){
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis()-start<=period){
        }
    }
}
