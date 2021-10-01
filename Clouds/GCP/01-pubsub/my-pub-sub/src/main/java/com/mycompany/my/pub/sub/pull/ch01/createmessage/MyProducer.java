package com.mycompany.my.pub.sub.pull.ch01.createmessage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author asusadmin
 */
public class MyProducer {

    public static void main(String[] args) throws Exception {
        String projectId = "mypsproject01-1615913146696";
        String topicId = "MyTopic";
        //projects/mypsproject01-1615913146696/topics/MyTopic
        
        //projectId = "my-pub-sub-327710";
        //topicId = "projects/my-pub-sub-327710/topics/MyTopicA";
        //topicId = "MyTopicA";
        publisherExample(projectId, topicId);
    }

    public static void publisherExample(String projectId, String topicId) throws Exception {
        TopicName topicName = TopicName.of(projectId, topicId);

        Publisher publisher = null;
        try {
            // Create a publisher instance with default settings bound to the topic
            publisher = Publisher.newBuilder(topicName).build();

            String message = "Hello World from Java "+System.currentTimeMillis();
            ByteString data = ByteString.copyFromUtf8(message);
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

            // Once published, returns a server-assigned message id (unique within the topic)
            ApiFuture<String> messageIdFuture = publisher.publish(pubsubMessage);
            String messageId = messageIdFuture.get();
            System.out.println("Published message ID: " + messageId);
        } finally {
            if (publisher != null) {
                // When finished with the publisher, shutdown to free up resources.
                publisher.shutdown();
                publisher.awaitTermination(1, TimeUnit.MINUTES);
            }
        }
    }

}
