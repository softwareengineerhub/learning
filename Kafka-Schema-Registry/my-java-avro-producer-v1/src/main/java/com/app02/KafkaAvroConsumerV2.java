package com.app02;

import com.example.Customer;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

public class KafkaAvroConsumerV2 {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.setProperty("group.id", "my-avro-consumer");
        properties.setProperty("enable.auto.commit", "false");
        properties.setProperty("auto.offset.reset", "earliest");

        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", KafkaAvroDeserializer.class.getName());
        properties.setProperty("value.deserializer", "http://127.0.0.1:8081");
        properties.setProperty("specific.avro.reader", "true");

        KafkaConsumer<String, Customer> consumer = new KafkaConsumer<String, Customer>(properties);
        String topic = "customer-avro";
        consumer.subscribe(Collections.singleton(topic));

        System.out.println("Waiting for data...");
        while(true){
            ConsumerRecords<String, Customer> records = consumer.poll(500);
            for(ConsumerRecord<String, Customer> record : records){
                Customer customer = record.value();
                System.out.println(customer);
            }
            consumer.commitSync();
        }
    }

}
