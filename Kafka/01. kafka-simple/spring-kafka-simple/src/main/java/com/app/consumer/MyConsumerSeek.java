package com.app.consumer;

import com.google.common.primitives.Longs;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.springframework.kafka.support.KafkaHeaders.*;

@Component
public class MyConsumerSeek implements ConsumerSeekAware {
    @Autowired
    private LagComponent lagComponent;

    @KafkaListener(topics = "my-topic", groupId = "test-group", id = "test")
    // @KafkaListener(topics = "t_topic", groupId = "default-spring-consumer", containerFactory = "kafkaListenerContainerFactory")
    public void onMessage(String message, @Header(OFFSET) long offset, @Header(RECEIVED_PARTITION_ID) int partitionId, @Header(RECEIVED_TOPIC) String topic, KafkaConsumer kafkaConsumer, ConsumerRecord record, @Header(value = "currentTime", required = false) byte[] currentTimestamp) {
        try {
            Map endOffsetsMap = kafkaConsumer.endOffsets(lagComponent.getPartitions());
            lagComponent.createEndOffsetMap(endOffsetsMap);
            //lagComponent.updateOffsetMetaInfo(partitionId, offset);
            Thread.sleep(8000);
            lagComponent.updateOffsetMetaInfo(partitionId, offset);
            System.out.println("Consumed: " + message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void registerSeekCallback(ConsumerSeekCallback consumerSeekCallback) {
        //consumerSeekCallback.seekToBeginning("", 1);
    }

    @Override
    public void onPartitionsAssigned(Map<TopicPartition, Long> map, ConsumerSeekCallback consumerSeekCallback) {
        System.out.println("onPartitionsAssigned---------start");
        for (TopicPartition tp : map.keySet()) {
            System.out.println(tp + "=" + map.get(tp));
        }
        System.out.println("onPartitionsAssigned---------end");
        lagComponent.creatCommittedOffsetMap(map);
    }

    @Override
    public void onIdleContainer(Map<TopicPartition, Long> map, ConsumerSeekCallback consumerSeekCallback) {

    }
}
