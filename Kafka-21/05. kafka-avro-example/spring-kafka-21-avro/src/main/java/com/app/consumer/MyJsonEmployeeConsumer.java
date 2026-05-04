package com.app.consumer;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.avro.MyKey;
import org.example.avro.MyValue;
import org.springframework.kafka.annotation.KafkaListener;

//@Service
public class MyJsonEmployeeConsumer {

    @KafkaListener(topics = "tAvro")
    public void onMessage(ConsumerRecord<MyKey, MyValue> record) {
        System.out.println("----------MyJsonEmployeeConsumer------");
        System.out.println(String.format("key=%s; value=%s", record.key(), record.value()));
    }
}
