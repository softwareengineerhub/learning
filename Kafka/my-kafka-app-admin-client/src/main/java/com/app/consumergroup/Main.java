package com.app.consumergroup;

import kafka.admin.AdminClient;
import org.apache.kafka.clients.consumer.internals.ConsumerNetworkClient;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        String groupId = "qqq";
        String topicName = "the_topic";
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", groupId);

        //Properties props2 = new Properties();
        //props2.put("bootstrap.servers", bootstrapServer);
        AdminClient ac = AdminClient.create(props);

        //System.out.println(ac.ConsumerGroupSummary());
        //System.out.println(ac.ConsumerGroupSummary());
        kafka.admin.AdminClient.ConsumerGroupSummary summary = ac.describeConsumerGroup(groupId, 5000L);
        System.out.println(summary);
        System.out.println("summary.state()="+summary.state());
        scala.collection.immutable.List<AdminClient.ConsumerSummary> scalaList = summary.consumers().get();
        System.out.println("--------scalaList-----------");
        System.out.println(scalaList);
        System.out.println("--------csList-----------");
        List<AdminClient.ConsumerSummary> csList = scala.collection.JavaConversions.seqAsJavaList(scalaList);
        System.out.println(csList);

        //TopicPartitionsOffsetInfo topicPartitionsOffsetInfo = new TopicPartitionsOffsetInfo(ac);
        //new KafkaApiRequest(ac.client());
        //ConsumerNetworkClient networkClient = ac.client();

        List<String> topicNamesList = Arrays.asList(topicName);
        TopicPartitionsOffsetInfo topicPartitionsOffsetInfo = new TopicPartitionsOffsetInfo(ac);
        //Get Per broker topic info based on topic list
        topicPartitionsOffsetInfo.storeTopicPartitionPerNodeInfo(props, topicNamesList);
        System.out.println("-------endOffset-----------------");
        Map<Node, Map<TopicPartition, Long>> endOffsetMap = topicPartitionsOffsetInfo.findEndOffsets();
        System.out.println(endOffsetMap);
        Map<Node, Map<TopicPartition, Long>> committedOffsetMap = topicPartitionsOffsetInfo.findCommittedOffsets(groupId);
        System.out.println(committedOffsetMap);



        //networkClient.awaitMetadataUpdate();
    }

}
