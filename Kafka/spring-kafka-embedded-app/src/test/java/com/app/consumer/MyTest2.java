package com.app.consumer;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MyTest2 {

    @ClassRule
    public static EmbeddedKafkaRule embeddedKafka = new EmbeddedKafkaRule(1, true, "my_topic");

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Test
    public void test() throws InterruptedException {
        //embeddedKafka.getEmbeddedKafka().
        kafkaTemplate.send(new ProducerRecord("my-topic", "test-message"));
        System.out.println("message sent!!!");
        Thread.sleep(10000);
    }

    public static class MyTestConfig {

        /*@Bean
        public KafkaTemplate kafkaTemplate() {
            KafkaTemplate kafkaTemplate = new KafkaTemplate(producerFactory());
            return kafkaTemplate;
        }*/

        @Bean
        public ProducerFactory<String, String> producerFactory() {
            Map<String, Object> configProps = new HashMap<>();
            configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, embeddedKafka.getEmbeddedKafka().getBrokersAsString());
            configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            return new DefaultKafkaProducerFactory<>(configProps);
        }
    }


}
