package com.app.consumer;

import org.apache.kafka.common.TopicPartition;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class LagComponent {
    public Map<TopicPartition, Long> committedOffsetsMap = new HashMap<>();
    public Map<TopicPartition, Long> endOffsetsMap = new HashMap<>();

    public Collection<TopicPartition> getPartitions(){
        return committedOffsetsMap.keySet();
    }

    public void creatCommittedOffsetMap(Map<TopicPartition, Long> committedOffsetsMap) {
        //this.committedOffsetsMap = committedOffsetsMap;
        this.committedOffsetsMap.putAll(committedOffsetsMap);
    }

    public void createEndOffsetMap(Map<TopicPartition, Long> endOffsetsMap) {
        //this.endOffsetsMap = endOffsetsMap;
        this.endOffsetsMap.putAll(endOffsetsMap);
        System.out.println("@<endOffsetsMap="+endOffsetsMap);
    }

    public void updateOffsetMetaInfo(int partition, long offset) {
        try {
            for (TopicPartition tp : committedOffsetsMap.keySet()) {
                if (tp.partition() == partition) {
                    committedOffsetsMap.put(tp, offset);
                    return;
                }
            }
        }finally {
            System.out.println("@<committedOffsetsMap="+committedOffsetsMap);
        }
    }

    public long calculateLag() {
        System.out.println("\t\tcalculateLag----------start");
        long lag = 0;
        if(endOffsetsMap==null){
            return lag;
        }
        System.out.println("endOffsetsMap="+endOffsetsMap);
        System.out.println("committedOffsetsMap="+committedOffsetsMap);
        for(TopicPartition tp: endOffsetsMap.keySet()){
            Long committed = committedOffsetsMap.get(tp);
            if(committed== null){
                committed = 0L;
            }
            long tmp = endOffsetsMap.get(tp)-committed;
            lag+=tmp;
        }
        System.out.println("\t\tcalculateLag----------finish");
        return lag-1;
    }

}
