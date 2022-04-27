package com.app.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;
import java.util.Set;

@RestController
public class LagController {
    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @GetMapping("/m")
    public void m(@RequestParam String id) {
        Set set = kafkaListenerEndpointRegistry.getListenerContainerIds();
        MessageListenerContainer messageListenerContainer = kafkaListenerEndpointRegistry.getListenerContainer(id);
        ContainerProperties containerProperties = messageListenerContainer.getContainerProperties();
        Properties props = containerProperties.getConsumerProperties();
        //messageListenerContainer.pause();
        //messageListenerContainer.resume();
        Object listener = containerProperties.getMessageListener();
        //System.out.println(listener instanceof Ka);
        //RecordMessagingMessageListenerAdapter adapter = null;
        //adapter.`
        String groupId = messageListenerContainer.getGroupId();
        System.out.println("groupId=" + groupId);
    }

    @GetMapping("/pause")
    public void pause(@RequestParam String id) {
        MessageListenerContainer messageListenerContainer = kafkaListenerEndpointRegistry.getListenerContainer(id);
        messageListenerContainer.pause();
    }

    @GetMapping("/resume")
    public void resume(@RequestParam String id) {
        MessageListenerContainer messageListenerContainer = kafkaListenerEndpointRegistry.getListenerContainer(id);
        messageListenerContainer.resume();
    }


}
