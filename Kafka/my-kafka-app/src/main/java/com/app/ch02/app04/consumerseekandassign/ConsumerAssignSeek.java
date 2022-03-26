/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.ch02.app04.consumerseekandassign;

import com.app.ch02.app01.consumerdemo.ConsumerDemo;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author asusadmin
 */
public class ConsumerAssignSeek {
    
    
     public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(ConsumerDemo.class.getName());
        String bootstrapServers = "127.0.0.1:9092";
      //  String groupId="my-fourth-application-2";
        
        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        //props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        
        //create consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        
        //subscribe consumer to our topic
       // consumer.subscribe(Collections.singleton("first_topic"));
       
       
       //assign and seek are mostly used to replay data or fetch a specific message
       
       //assign:
       TopicPartition topicPartitionToReadFrom = new TopicPartition("first_topic", 0);
       consumer.assign(Arrays.asList(topicPartitionToReadFrom));
       
       //seek
       long offsetToReadFrom = 15L;
       consumer.seek(topicPartitionToReadFrom, offsetToReadFrom);
       
       int numberOfMessagesToRead = 5;
       boolean keepOnReading = true;
       int numberOfMessagesReadSoFar = 0;
        
        while(keepOnReading){
            /*ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for(ConsumerRecord<String, String> record: records){
                numberOfMessagesReadSoFar++;
                System.out.println("------------------------");
                System.out.println("Key: "+record.key()+", Value: "+record.value());
                System.out.println("Partition: "+record.partition()+", Offset:"+record.offset());
                System.out.println("------------------------");
                if(numberOfMessagesReadSoFar>=numberOfMessagesToRead){
                    keepOnReading=false;
                }
            }*/
        }
        
    }
    
}
