package org.example.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.entity.MyEntity;
import org.example.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MyKafkaConsumer {
    @Autowired
    private MyService myService;

    @KafkaListener(topics = "datatopic")
    public void onMessage(ConsumerRecord<MyKey, MyValue> record) {
        MyValue myValue = record.value();
        MyEntity myEntity = new MyEntity(null, myValue.name(), myValue.age(), myValue.description(), null);
        myService.addData(myEntity);
    }

}
