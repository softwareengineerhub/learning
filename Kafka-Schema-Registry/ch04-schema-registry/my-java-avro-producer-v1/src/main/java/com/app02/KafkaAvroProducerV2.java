package com.app02;

import com.example.Customer;
import com.example.CustomerV2;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaAvroProducerV2 {


    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.setProperty("ack", "1");
        properties.setProperty("retries", "10");

        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
        properties.setProperty("schema.registry.url", "http://127.0.0.1:8081");

        KafkaProducer<String, CustomerV2> kafkaProducer = new KafkaProducer<String, CustomerV2>(properties);
        String topic = "customer-avro";

        CustomerV2 customer = CustomerV2.newBuilder().setFirstName("FN").setLastName("LN").setAge(26)
                .setHeight(1.0f).setWeight(2.0f).setEmail("my@email").build();

        ProducerRecord<String, CustomerV2> producerRecord = new ProducerRecord<String, CustomerV2>(topic, customer);
        //kafkaProducer.send(producerRecord);
        kafkaProducer.send(producerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e == null) {
                    System.out.println("Success");
                    System.out.println(recordMetadata.toString());
                } else {
                    e.printStackTrace();
                }
            }
        });
        kafkaProducer.flush();
        kafkaProducer.close();
    }

}
