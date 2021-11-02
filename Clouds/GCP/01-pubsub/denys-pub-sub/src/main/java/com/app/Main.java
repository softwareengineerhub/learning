package com.app;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.pubsub.v1.ListTopicsRequest;
import com.google.pubsub.v1.ListTopicsResponse;
import com.google.pubsub.v1.PublisherGrpc;
import com.google.pubsub.v1.Topic;
import io.grpc.ClientInterceptors;
import io.grpc.auth.ClientAuthInterceptor;
import io.grpc.internal.ManagedChannelImpl;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;
import io.grpc.Channel;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

  public static void main(final String[] args) throws Exception {
    String projectId = "mypsproject01-1615913146696";
    String subscriptionId = "my-test-java-subscription";


    ManagedChannelImpl channelImpl = NettyChannelBuilder
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

    //publisherStub.getTopic()
    ListTopicsResponse resp = publisherStub.listTopics(request);
    System.out.println("Found " + resp.getTopicsCount() + " topics.");
    for (Topic topic : resp.getTopicsList()) {
      System.out.println(topic.getName());
    }
  }
}