package com.app.consumer;

import com.app.model.Employee;
import com.app.model.EmployeeKey;
import com.app.model.TestKey;
import com.app.model.TestValue;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MyJsonEmployeeConsumer {

    @KafkaListener(topics = "temployee", groupId = "a")
    public void onMessage(ConsumerRecord<EmployeeKey, Employee> record) {
        System.out.println("----------MyJsonEmployeeConsumer------");
        System.out.println(String.format("key=%s; value=%s", record.key(), record.value()));
    }

    /*@KafkaListener(topics = "ttest", groupId = "a")
    public void onMessage2(ConsumerRecord<TestKey, TestValue> record) {
        System.out.println("----------MyJsonTestConsumer------");
        System.out.println(String.format("key=%s; value=%s", record.key(), record.value()));
    }*/

    @KafkaListener(topics = "ttest", groupId = "a")
    public void onMessage2(TestValue value) {
        System.out.println("----------MyJsonTestConsumer------");
        System.out.println(String.format("value=%s", value));
    }
}
