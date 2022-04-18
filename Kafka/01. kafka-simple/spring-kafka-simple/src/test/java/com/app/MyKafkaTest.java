package com.app;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
//@EmbeddedKafka
@RunWith(SpringRunner.class)
public class MyKafkaTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void test(){
        KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, "t_topic");
        kafkaTemplate.send("t_topic", "test-message");
        //embeddedKafka.
        //EmbeddedKafkaBroker embeddedKafkaBroker = new EmbeddedKafkaBroker();
    }

}
