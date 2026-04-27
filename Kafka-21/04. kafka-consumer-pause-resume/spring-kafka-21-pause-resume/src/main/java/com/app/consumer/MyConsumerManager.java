package com.app.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MyConsumerManager {
    @Autowired
    private KafkaListenerEndpointRegistry registry;

    public void pause() {
        System.out.println("pause()");
        Collection<MessageListenerContainer> allListenerContainers = registry.getAllListenerContainers();
        for (MessageListenerContainer container : allListenerContainers) {
            System.out.println("Container.groupId=" + container.getGroupId());
            container.pause();
        }
    }

    public void resume() {
        System.out.println("resume()");
        Collection<MessageListenerContainer> allListenerContainers = registry.getAllListenerContainers();
        for (MessageListenerContainer container : allListenerContainers) {
            System.out.println("Container.groupId=" + container.getGroupId());
            container.resume();
        }
    }
}
