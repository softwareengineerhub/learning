package com.app.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;

import static org.springframework.kafka.support.KafkaHeaders.*;

//@Component
public class MyConsumerSeekWithDefinedKafkaConsumer implements ConsumerSeekAware {
  //  @Autowired
    private LagComponent lagComponent;
    private KafkaConsumer kafkaConsumerGlobal;

    //@KafkaListener(topics = "my-topic", groupId = "test-group", id = "test")
    //@KafkaListener(topics = "t_topic", groupId = "default-spring-consumer", containerFactory = "kafkaListenerContainerFactory")
    public void onMessage(String message, @Header(OFFSET) long offset, @Header(RECEIVED_PARTITION_ID) int partitionId, @Header(RECEIVED_TOPIC) String topic, ConsumerRecord record, @Header(value = "currentTime", required = false) byte[] currentTimestamp) {
        try {
            Map endOffsetsMap = kafkaConsumerGlobal.endOffsets(lagComponent.getPartitions());
            lagComponent.createEndOffsetMap(endOffsetsMap);
            lagComponent.updateOffsetMetaInfo(partitionId, offset);
            Thread.sleep(8000);
            System.out.println("Consumed: " + message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        long lag = lagComponent.calculateLag();
        System.out.println("!!!!!!!lag=" + lag + "!!!!!!!");
    }

    @Override
    public void registerSeekCallback(ConsumerSeekCallback consumerSeekCallback) {
        //consumerSeekCallback.seekToBeginning("", 1);
        //consumerSeekCallback.
    }

    @Override
    public void onPartitionsAssigned(Map<TopicPartition, Long> map, ConsumerSeekCallback consumerSeekCallback) {
        System.out.println("onPartitionsAssigned---------start");
        for (TopicPartition tp : map.keySet()) {
            System.out.println(tp + "=" + map.get(tp));
        }
        System.out.println("onPartitionsAssigned---------end");
        //KafkaConsumer kc = null;

        lagComponent.creatCommittedOffsetMap(map);


        try {
            //Field modifiersField = consumerSeekCallback.getClass().getDeclaredField("cosumer");
            //modifiersField.setAccessible(true);
            Field[] fields = consumerSeekCallback.getClass().getDeclaredFields();
            System.out.println("------------------");
            for(Field f: fields) {
                System.out.println(f.getName());
                f.setAccessible(true);
                //consumerSeekCallback.
                Object obj = f.get(consumerSeekCallback);
                System.out.println("obj="+obj);
                Field f1 = obj.getClass().getDeclaredField("consumer");
                f1.setAccessible(true);
                Object obj2 = f1.get(obj);
                System.out.println(obj2);
                if(obj2 instanceof KafkaConsumer){
                    this.kafkaConsumerGlobal = (KafkaConsumer) obj2;
                }
            }
            System.out.println("------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }


        //consumerSeekCallback.getClass().getde
        //System.out.println();

    }

    @Override
    public void onIdleContainer(Map<TopicPartition, Long> map, ConsumerSeekCallback consumerSeekCallback) {

    }
}
