/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.ch02.app01.consumerdemo;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author asusadmin
 */
public class ConsumerDemo {
    
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(ConsumerDemo.class.getName());
        String bootstrapServers = "127.0.0.1:9092";
        String groupId="my-fourth-application";
        
        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        
        //create consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        
        //subscribe consumer to our topic
        consumer.subscribe(Collections.singleton("my_topic"));

        while(true){
           /* ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for(ConsumerRecord<String, String> record: records){
                System.out.println("offset="+record.offset());
                System.out.println("------------------------");
                System.out.println("Key: "+record.key()+", Value: "+record.value());
                System.out.println("Partition: "+record.partition()+", Offset:"+record.offset());
                System.out.println("------------------------");
            }*/
        }
    }

    private static void m(){
        AdminClient adminClient = null;
        //adminClient.
    }
    
}
