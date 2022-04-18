package com.app.consumergroup;

import kafka.admin.AdminClient;
import org.apache.kafka.clients.ClientResponse;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.consumer.internals.RequestFuture;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.TopicPartitionInfo;
import org.apache.kafka.common.requests.*;

import java.util.*;

public class TopicPartitionsOffsetInfo {
    private final AdminClient adminClient;
    private final KafkaApiRequest kafkaApiRequest;
    private Map<Node, List<TopicPartition>> nodePartitionMap = new HashMap<>();

    public TopicPartitionsOffsetInfo(final AdminClient adminClient) {
        this.adminClient = adminClient;
        this.kafkaApiRequest = new KafkaApiRequest(adminClient.client());
    }

    public void storeTopicPartitionPerNodeInfo(final Properties props, final List<String> topicNamesList) {
        org.apache.kafka.clients.admin.AdminClient ac = org.apache.kafka.clients.admin.AdminClient.create(props);
        DescribeTopicsResult describeTopicsResult = ac.describeTopics(topicNamesList);
        try {
            Map<String, TopicDescription> topicDescriptionMap = describeTopicsResult.all().get();
            for (String key : topicDescriptionMap.keySet()) {
                TopicDescription topicDescription = topicDescriptionMap.get(key);
                for (TopicPartitionInfo partitionInfo : topicDescription.partitions()) {
                    Node leaderNode = partitionInfo.leader();
                    List<TopicPartition> topicPartitionList = nodePartitionMap.get(leaderNode);
                    if (topicPartitionList == null) {
                        topicPartitionList = new ArrayList<>();
                    }
                    int partition = partitionInfo.partition();
                    TopicPartition topicPartition = new TopicPartition(key, partition);
                    topicPartitionList.add(topicPartition);
                    nodePartitionMap.put(leaderNode, topicPartitionList);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Node, Map<TopicPartition, Long>> findEndOffsets() {
        Map<Node, Map<TopicPartition, Long>> resultMap = new HashMap<>();
        for (Node node : nodePartitionMap.keySet()) {
            List<TopicPartition> topicPartitionList = nodePartitionMap.get(node);
            Map<TopicPartition, Long> map = findEndOffsets(node, topicPartitionList);
            resultMap.put(node, map);
        }
        return resultMap;
    }

    private Map<TopicPartition, Long> findEndOffsets(Node leaderNode, List<TopicPartition> topicPartitionList) {
        Map<TopicPartition, Long> requiredTimestamp = new HashMap<>();
        for (TopicPartition topicPartition : topicPartitionList) {
            requiredTimestamp.put(topicPartition, -1L);
        }
        ListOffsetRequest.Builder builder = ListOffsetRequest.Builder
                .forConsumer(false, IsolationLevel.READ_UNCOMMITTED)
                .setTargetTimes(requiredTimestamp);
        RequestFuture<ClientResponse> requestFuture = kafkaApiRequest.sendApiRequest(leaderNode, builder);

        while (!requestFuture.isDone()) {
        }
        ClientResponse clientResponse = requestFuture.value();
        ListOffsetResponse listOffsetResponse = (ListOffsetResponse) clientResponse.responseBody();
        Map<TopicPartition, ListOffsetResponse.PartitionData> responseData = listOffsetResponse.responseData();
        Map<TopicPartition, Long> result = new HashMap<>();
        for (TopicPartition topicPartition : responseData.keySet()) {
            ListOffsetResponse.PartitionData partitionData = responseData.get(topicPartition);
            long offset = partitionData.offset;
            result.put(topicPartition, offset);
        }
        return result;
    }

    public Map<Node, Map<TopicPartition, Long>> findCommittedOffsets(String groupName) {
        Map<Node, Map<TopicPartition, Long>> resultMap = new HashMap<>();
        for (Node node : nodePartitionMap.keySet()) {
            List<TopicPartition> topicPartitionList = nodePartitionMap.get(node);
            Map<TopicPartition, Long> map = findCommittedOffsets(groupName, node, topicPartitionList);
            resultMap.put(node, map);
        }
        return resultMap;
    }

    private Map<TopicPartition, Long> findCommittedOffsets(String groupName, Node leaderNode, List<TopicPartition> topicPartitionList) {
        OffsetFetchRequest.Builder offsetRequestBuilder = new OffsetFetchRequest.Builder(groupName, topicPartitionList);
        RequestFuture<ClientResponse> requestFuture = kafkaApiRequest.sendApiRequest(leaderNode, offsetRequestBuilder);
        while (!requestFuture.isDone()) {
        }
        ClientResponse clientResponse = requestFuture.value();
        OffsetFetchResponse offsetFetchResponse = (OffsetFetchResponse) clientResponse.responseBody();
        Map<TopicPartition, OffsetFetchResponse.PartitionData> responseData = offsetFetchResponse.responseData();
        Map<TopicPartition, Long> result = new HashMap<>();
        for (TopicPartition topicPartition : responseData.keySet()) {
            OffsetFetchResponse.PartitionData partitionData = responseData.get(topicPartition);
            long offset = partitionData.offset;
            result.put(topicPartition, offset);
        }
        return result;
    }

    /*public void run() {
        if(this.offsetRequestValue == TopicPartitionsOffsetInfo.END_OFFSET_VALUE) {
            this.requestNprocessEndOffsets();
        }else {
            this.requestNprocessBeginningOffsets();
        }
        this.countDownLatch.countDown();
    }*/

}
