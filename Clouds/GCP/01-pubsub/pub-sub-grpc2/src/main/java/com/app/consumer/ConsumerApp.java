package com.app.consumer;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.pubsub.v1.*;
import io.grpc.Channel;
import io.grpc.ClientInterceptors;
import io.grpc.ManagedChannel;
import io.grpc.auth.ClientAuthInterceptor;
import io.grpc.netty.shaded.io.grpc.netty.NegotiationType;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConsumerApp {

    public static void main(final String[] args) throws Exception {
        String projectId = "mypsproject01-1615913146696";
        String subscriptionId = "my-test-java-subscription";

        ManagedChannel channelImpl = NettyChannelBuilder
                .forAddress("pubsub.googleapis.com", 443)
                .negotiationType(NegotiationType.TLS)
                .build();

        GoogleCredentials creds = GoogleCredentials.getApplicationDefault();
        // Down-scope the credential to just the scopes required by the service
        creds = creds.createScoped(Arrays.asList("https://www.googleapis.com/auth/pubsub"));
        // Intercept the channel to bind the credential
        ExecutorService executor = Executors.newSingleThreadExecutor();
        ClientAuthInterceptor interceptor = new ClientAuthInterceptor(creds, executor);
        Channel channel = ClientInterceptors.intercept(channelImpl, interceptor);
        // Create a stub using the channel that has the bound credential

        PublisherGrpc.PublisherBlockingStub publisherStub = PublisherGrpc.newBlockingStub(channel);
        //PublisherGrpc.PublisherStub publisherStub = PublisherGrpc.newStub(channel);
        ListTopicsRequest request = ListTopicsRequest.newBuilder()
                .setPageSize(10)
                .setProject("projects/" + projectId)
                .build();

        SubscriberGrpc.SubscriberBlockingStub subscriberStub = SubscriberGrpc.newBlockingStub(channel);

        PullRequest pullRequest = PullRequest.newBuilder().setMaxMessages(10)
                .setSubscription("projects/mypsproject01-1615913146696/subscriptions/my-test-java-subscription").build();
        PullResponse response = subscriberStub.pull(pullRequest);

        int receivedMessagesCount = response.getReceivedMessagesCount();
        System.out.println("receivedMessagesCount=" + receivedMessagesCount);
        System.out.println("response=" + response);
        System.out.println("--------------need to ack---------------");

        List<ReceivedMessage> messages = response.getReceivedMessagesList();
        List<String> ackIds = new ArrayList<>();
        for (ReceivedMessage msg : messages) {
            String ackId = msg.getAckId();
            System.out.println("ackId=" + ackId);
            ackIds.add(ackId);
        }

        if (!ackIds.isEmpty()) {
            AcknowledgeRequest acknowledgeRequest = AcknowledgeRequest.newBuilder()
                    .setSubscription("projects/mypsproject01-1615913146696/subscriptions/my-test-java-subscription")
                    .addAllAckIds(ackIds).build();
            subscriberStub.acknowledge(acknowledgeRequest);
            System.out.println("ack completed: " + ackIds);
        }


    }

}
