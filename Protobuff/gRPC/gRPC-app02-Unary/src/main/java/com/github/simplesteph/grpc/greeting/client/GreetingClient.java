package com.github.simplesteph.grpc.greeting.client;

import com.github.simplesteph.grpc.greeting.server.GreetingServer;
import com.proto.dummy.DummyServiceGrpc;
import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {

    public static void main(String[] args) {
        System.out.println("Hello I'm gRPC client");

        //usePlaintext() - disbales SSL
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext().build();

        System.out.println("Creating Stub");

        //DummyServiceGrpc.DummyServiceBlockingStub syncClient = DummyServiceGrpc.newBlockingStub(channel);
        //DummyServiceGrpc.DummyServiceFutureStub asynchClient = DummyServiceGrpc.newFutureStub(channel);

        GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);

        //do something
        Greeting greeting = Greeting.newBuilder().setFirstName("Name1").setLastName("LastName1").build();
        GreetRequest request = GreetRequest.newBuilder().setGreeting(greeting).build();
        //greetClient.greet(request);

        GreetResponse response = greetClient.greet(request);
        System.out.println("Response: "+response.getResult());

        System.out.println("-------Shutting down channel-------");
        channel.shutdown();

    }
}
