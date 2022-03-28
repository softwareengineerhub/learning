package com.app.consumer;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import static org.springframework.kafka.support.KafkaHeaders.OFFSET;

@Component
public class MyConsumer {

    @KafkaListener(topics = "t_topic", groupId = "default-spring-consumer")
    public void onMessage(String message, @Header(OFFSET) long offset, KafkaConsumer kafkaConsumer){
        System.out.println(hashCode()+" ;message="+message+"; OFFSET="+offset+"; consumer="+kafkaConsumer);
    }
}
