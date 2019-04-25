package com.app.ch01.app03.producerdemokeys;

import com.app.ch01.app02.producerwithcallback.ProducerWithCallbackDemo;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemoKeys {

    private static Logger logger = LoggerFactory.getLogger(ProducerWithCallbackDemo.class);

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer(props);



        int i=0;
        String topic = "first_topic";
        String value = "ProducerC "+i;
        String key = "id_"+i;

        ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, key, value);

        producer.send(record, new Callback() {
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e == null) {
                    //record was successfully sent and we can wait for metadata
                    logger.info("Received new metadata: ]n" +
                            "Topic:" + recordMetadata.topic() + "\n" +
                            "Partition:" + recordMetadata.partition() + "\n" +
                            "Offset:" + recordMetadata.offset() + "\n" +
                            "Timestamp:" + recordMetadata.timestamp() + "\n");
                } else {
                    e.printStackTrace();
                }

            }
        });
        producer.close();
    }

}
