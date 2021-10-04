package com.github.simplesteph.grpc.greeting.client;

import com.proto.greet.*;
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

        Greeting greeting = Greeting.newBuilder().setFirstName("Name1").setLastName("LastName1").build();
        GreetManyTimesRequest request = GreetManyTimesRequest.newBuilder().setGreeting(greeting).build();

        //we stream responses (in a blocking manner)
        greetClient.greetManyTimes(request).forEachRemaining(response->{
            System.out.println("Response: " + response.getResult());
        });


        System.out.println("-------Shutting down channel-------");
        channel.shutdown();

    }
}
