package com.app.consumer;

import kafka.admin.AdminClient;
import org.apache.kafka.common.TopicPartition;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;

import static org.apache.kafka.clients.CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG;

@Component
public class LagComponent {
    private Map<TopicPartition, Long> committedOffsetsMap;
    private Map<TopicPartition, Long> endOffsetsMap;
    private AdminClient adminClient;

    public LagComponent() {
        Properties props = new Properties();
        props.put(BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        adminClient = AdminClient.create(props);
    }

    public Collection<TopicPartition> getPartitions(){
        return committedOffsetsMap.keySet();
    }

    public void creatCommittedOffsetMap(Map<TopicPartition, Long> committedOffsetsMap) {
        this.committedOffsetsMap = committedOffsetsMap;
    }

    public void createEndOffsetMap(Map<TopicPartition, Long> endOffsetsMap) {
        this.endOffsetsMap = endOffsetsMap;
    }

    public void updateOffsetMetaInfo(int partition, long offset) {
        for (TopicPartition tp : committedOffsetsMap.keySet()) {
            if (tp.partition() == partition) {
                committedOffsetsMap.put(tp, offset);
                return;
            }
        }
    }

    public long calculateLag() {
        long lag = 0;
        if(endOffsetsMap==null){
            return lag;
        }
        for(TopicPartition tp: endOffsetsMap.keySet()){
            Long committed = committedOffsetsMap.get(tp);
            if(committed== null){
                committed = 0L;
            }
            long tmp = endOffsetsMap.get(tp)-committed;
            lag+=tmp;
        }
        return lag;
    }

}
