/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.ch02.app03.consumerthreads;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 *
 * @author asusadmin
 */
public class ConsumerThreads {

    public static void main(String[] args) throws InterruptedException {
       
        
        
        
        
        CountDownLatch countDownLatch = new CountDownLatch(1);
        String bootstrapServers = "127.0.0.1:9092";
        String groupId = "my-fourth-application3";
        String topic = "first_topic";
        final ConsumerRunnable cr = new ConsumerRunnable(countDownLatch, bootstrapServers, groupId, topic);
        
        
         Runtime.getRuntime().addShutdownHook(new Thread(){
            
            @Override
            public void run(){
                cr.shutDown();
            }
        });
        
        Thread t1 = new Thread(cr);
        t1.start();
        
        
        countDownLatch.await();
    }

    public static class ConsumerRunnable implements Runnable {

        private CountDownLatch countDownLatch;
        private KafkaConsumer<String, String> consumer;

        public ConsumerRunnable(CountDownLatch countDownLatch, String bootstrapServers,String groupId, String topic) {
            this.countDownLatch = countDownLatch;

            Properties props = new Properties();
            props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
            props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

            consumer = new KafkaConsumer<String, String>(props);
            consumer.subscribe(Collections.singleton(topic));
            //subscribe consumer to our topic
            //consumer.subscribe(Collections.singleton("first_topic"));
        }

        public void run() {
            try {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.println("");
                        System.out.println("------------------------");
                        System.out.println("Key: " + record.key() + ", Value: " + record.value());
                        System.out.println("Partition: " + record.partition() + ", Offset:" + record.offset());
                        System.out.println("------------------------");
                    }
                }
            } catch (WakeupException ex) {
                System.out.println("shutDown signal");
            } finally {
                consumer.close();
                countDownLatch.countDown();
            }
        }

        public void shutDown() {
            //special method to interrupt consumer.poll()
            consumer.wakeup();
        }

    }

}
