/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.ch02.app01.consumerdemo;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 *
 * @author asusadmin
 */
public class ConsumerDemoLag {
    
    public static void main(String[] args) throws Exception {
        Logger logger = LoggerFactory.getLogger(ConsumerDemoLag.class.getName());
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


        System.out.println("----------partition-infos------------");
        List<PartitionInfo> partitionInfos = consumer.partitionsFor("my_topic");
        for(PartitionInfo partitionInfo: partitionInfos){
            System.out.println(partitionInfo);
        }

        System.out.println("----------topic-partitions------------");
        List<TopicPartition> partitions = new ArrayList<>();
        TopicPartition topicPartition = new TopicPartition("my_topic", 0);
        partitions.add(topicPartition);
        Map<TopicPartition, Long> map = consumer.endOffsets(partitions);
        System.out.println("----------endOffsets------------");
        long endOffset = -1;
        for(TopicPartition tp: map.keySet()){
            System.out.println(tp+"="+map.get(tp));
            endOffset = map.get(tp);
        }

        System.out.println("----------beginingOffsets------------");
        map = consumer.beginningOffsets(partitions);
        long beginningOffset = -1;
        for(TopicPartition tp: map.keySet()){
            System.out.println(tp+"="+map.get(tp));
            beginningOffset = map.get(tp);
        }

        System.out.println("----------beginingOffsets------------");
        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        AdminClient adminClient = AdminClient.create(config);
        ListConsumerGroupOffsetsResult list = adminClient.listConsumerGroupOffsets(groupId);
        KafkaFuture<Map<TopicPartition, OffsetAndMetadata>> kafkaFuture = list.partitionsToOffsetAndMetadata();
        Map<TopicPartition, OffsetAndMetadata> offsetMap = kafkaFuture.get();
        System.out.println("-------------offsetMap---------");
        System.out.println(offsetMap);
        System.out.println("-------------lag-----------");
        for(TopicPartition tp: offsetMap.keySet()) {
            long offset = offsetMap.get(tp).offset();
            long lag = endOffset - offset;
            System.out.println(tp.topic()+"-"+tp.partition()+";lag=" + lag);
        }

        System.out.println("-------------consumer group description-----------");
        DescribeConsumerGroupsResult describeConsumerGroupsResult = adminClient.describeConsumerGroups(Arrays.asList(groupId));
        //Map<String, >
        Map<String, KafkaFuture<ConsumerGroupDescription>> consumerGroupDescription = describeConsumerGroupsResult.describedGroups();
        for(String key: consumerGroupDescription.keySet()){
            KafkaFuture<ConsumerGroupDescription> future = consumerGroupDescription.get(key);
            ConsumerGroupDescription cgd = future.get();
            System.out.println(key+"="+cgd);
        }
        System.out.println("----------------listConsumerGroupsResult----------------");
        ListConsumerGroupsResult listConsumerGroupsResult = adminClient.listConsumerGroups();
        Collection<ConsumerGroupListing> consumerGroupsResult = listConsumerGroupsResult.all().get();
        System.out.println(consumerGroupsResult);
        for(ConsumerGroupListing item: consumerGroupsResult){
            System.out.println("groupId"+item.groupId());
        }
    }
    
}
