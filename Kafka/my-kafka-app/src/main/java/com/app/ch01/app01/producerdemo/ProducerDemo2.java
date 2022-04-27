package com.app.ch01.app01.producerdemo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerDemo2 {

    public static void main(String[] args) {

        //ROUND ROBIN
        Properties props = new Properties();
        /*props.setProperty("bootstrpa.servers","127.0.0.1:9092");
        //to byte[]
        props.setProperty("key.serializer", StringSerializer.class.getName());
        props.setProperty("value.serializer", StringSerializer.class.getName());
        props.setProperty("","");*/

        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG , "64.227.44.229:9092");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //props.put(ProducerConfig.ACKS_CONFIG, "-1");
        //props.put(ProducerConfig.ACKS_CONFIG, "0");
        //props.put(ProducerConfig.ACKS_CONFIG, "1");
        props.put(ProducerConfig.ACKS_CONFIG, "all");

        //key and value String
        KafkaProducer<String, String> producer = new KafkaProducer(props);


        ProducerRecord<String, String> record = new ProducerRecord<String, String>("t_topic", "qt_topicpool2");

        //send data - asynchronous
        //programm finishes before data is sent
        producer.send(record);
        producer.flush();
        /*for(int i=1;i<10;i++){
            producer.send(record);
            producer.flush();
        }*/

        //or
        producer.close();

    }
}

