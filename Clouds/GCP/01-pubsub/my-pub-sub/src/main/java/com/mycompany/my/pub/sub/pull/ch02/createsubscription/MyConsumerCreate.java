/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.my.pub.sub.pull.ch02.createsubscription;

import com.google.api.gax.rpc.AlreadyExistsException;
import com.google.cloud.pubsub.v1.SubscriptionAdminClient;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PushConfig;
import com.google.pubsub.v1.Subscription;
import com.google.pubsub.v1.TopicName;

/**
 *
 * @author asusadmin
 */
public class MyConsumerCreate {

    public static void main(String... args) throws Exception {
        String projectId = "mypsproject01-1615913146696";
        String subscriptionId = "my-test-java-subscription-B";
        String topicId = "MyTopic";
        try {
            createPullSubscriptionExample(projectId, subscriptionId, topicId);
        } catch (AlreadyExistsException ex) {
            ex.printStackTrace();
        }
        
    }

    public static void createPullSubscriptionExample(String projectId, String subscriptionId, String topicId) throws Exception {
        try (SubscriptionAdminClient subscriptionAdminClient = SubscriptionAdminClient.create()) {
            TopicName topicName = TopicName.of(projectId, topicId);
            ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(projectId, subscriptionId);
            Subscription subscription = subscriptionAdminClient.createSubscription(subscriptionName, topicName, PushConfig.getDefaultInstance(), 10);
            System.out.println("Created pull subscription: " + subscription.getName());
        }
    }

}
