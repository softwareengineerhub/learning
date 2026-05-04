package com.app.producer;

import org.example.avro.MyKey;
import org.example.avro.MyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MyAvroProducer {

    @Autowired
    private KafkaTemplate<MyKey, MyValue> kafkaTemplate;

    public void send(MyKey myKey, MyValue myValue) {
        System.out.println(String.format("EmployeeProducer.Sending key=%s; employee=%s", myKey, myValue));
        kafkaTemplate.send("tAvro", myKey, myValue);
    }
}
