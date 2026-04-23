package com.app.consumer;

import com.app.model.Employee;
import com.app.model.EmployeeKey;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MyJsonEmployeeConsumer {

    @KafkaListener(topics = "temployee")
    public void onMessage(ConsumerRecord<EmployeeKey, Employee> record) {
        System.out.println("----------MyJsonEmployeeConsumer------");
        System.out.println(String.format("key=%s; value=%s", record.key(), record.value()));
    }
}
