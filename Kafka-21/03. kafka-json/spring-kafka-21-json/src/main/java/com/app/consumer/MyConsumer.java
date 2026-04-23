package com.app.consumer;

import com.app.model.Employee;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class MyConsumer {

    @KafkaListener(topics = "temployee")
    public void onMessage(String employeeJson) {
        System.out.println("----------Consumed------");
        System.out.println("Consumed: " + employeeJson);
        //System.out.println(String.format("key=%s; value=%s", record.key(), record.value()));
    }
}
