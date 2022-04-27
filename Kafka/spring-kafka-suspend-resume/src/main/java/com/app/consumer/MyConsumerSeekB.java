package com.app.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import static org.springframework.kafka.support.KafkaHeaders.*;

@Component
public class MyConsumerSeekB {

    @KafkaListener(topics = "my-topic2", groupId = "myg", id = "testB")
    public void onMessage(String message, @Header(OFFSET) long offset, @Header(RECEIVED_PARTITION_ID) int partitionId, @Header(RECEIVED_TOPIC) String topic, KafkaConsumer kafkaConsumer, ConsumerRecord record, @Header(value = "currentTime", required = false) byte[] currentTimestamp) {
        System.out.println("ConsumerB=" + message);
    }
}
