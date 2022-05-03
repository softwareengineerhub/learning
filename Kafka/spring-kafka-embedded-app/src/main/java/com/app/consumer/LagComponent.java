package com.app.consumer;

import org.apache.kafka.common.TopicPartition;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

@Component
public class LagComponent {
    //JsonMapper km;
    private Map<TopicPartition, Long> committedOffsetsMap;
    private Map<TopicPartition, Long> endOffsetsMap;

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
