package com.app.producer;

import com.app.model.Employee;
import com.app.model.EmployeeKey;
import com.app.model.TestKey;
import com.app.model.TestValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TestProducer {

    @Autowired
    private KafkaTemplate<TestKey, TestValue> kafkaTemplate;

    public void send(TestKey key, TestValue value) {
        System.out.println(String.format("TestProducer.Sending key=%s; employee=%s", key, value));
        kafkaTemplate.send("ttest", key, value);
    }
}
