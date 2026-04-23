package com.app.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MyConsumer {

    @KafkaListener(topics = "my-topic", concurrency = "5")
    public void onMessage(ConsumerRecord<String, String> record) {
        System.out.println("----------Consumed------");
        System.out.println(String.format("record=%s", record));
        System.out.println(String.format("key=%s; value=%s", record.key(), record.value()));
    }
}
