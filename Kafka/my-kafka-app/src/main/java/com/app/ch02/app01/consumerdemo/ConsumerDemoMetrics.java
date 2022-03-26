/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.ch02.app01.consumerdemo;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.*;

/**
 *
 * @author asusadmin
 */
public class ConsumerDemoMetrics {
    
    public static void main(String[] args) throws Exception {
        Logger logger = LoggerFactory.getLogger(ConsumerDemoMetrics.class.getName());
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
        List<TopicPartition> partitions = new ArrayList<>();
        TopicPartition topicPartition = new TopicPartition("my_topic", 0);
        partitions.add(topicPartition);
        Map<TopicPartition, Long> map = consumer.endOffsets(partitions);
        System.out.println("map="+map);
        System.out.println("----------------");
        for(TopicPartition tp: map.keySet()){
            System.out.println("tp="+map.get(tp));
        }
        System.out.println("map="+map);


        //subscribe consumer to our topic
        //while(true) {
            consumer.subscribe(Collections.singleton("my_topic"));
         //ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            Map<MetricName, ? extends Metric> mapMetrics = consumer.metrics();
            for (MetricName name : mapMetrics.keySet()) {
                if (name.name().contains("lag")) {
                    System.out.println("!!!" + name.name()+"="+map.get(mapMetrics));
                }
            }
            Thread.sleep(3000);
        //}

        System.out.println("mapMetrics="+mapMetrics);
        System.out.println("----records-lag------");
        System.out.println("records-lag="+mapMetrics.get("records-lag"));
        //System.out.println("records-lag="+mapMetrics.get("log-end-offset".toUpperCase()));
    }
    
}
