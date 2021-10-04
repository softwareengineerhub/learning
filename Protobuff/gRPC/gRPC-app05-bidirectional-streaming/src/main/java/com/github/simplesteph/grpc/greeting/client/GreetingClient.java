package com.github.simplesteph.grpc.greeting.client;

import com.proto.greet.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.ClientResponseObserver;
import io.grpc.stub.StreamObserver;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class GreetingClient {


    private void run() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext().build();
        doBidirectionalStreamingCall(channel);
    }

    private void doBidirectionalStreamingCall(ManagedChannel channel) {
        GreetServiceGrpc.GreetServiceStub asyncClient = GreetServiceGrpc.newStub(channel);

        CountDownLatch latch = new CountDownLatch(1);
        StreamObserver<GreetEveryoneRequest> requestObserver = asyncClient.greetEveryone(new StreamObserver<GreetEveryoneResponse>() {


            @Override
            public void onNext(GreetEveryoneResponse value) {
                System.out.println("Response from server: " + value.getResult());
            }

            @Override
            public void onError(Throwable t) {
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                System.out.println("Server is done sending data");
                latch.countDown();
            }
        });

        Arrays.asList("Name1", "Name2", "Name3", "Name4").forEach(name -> {
            Greeting greeting = Greeting.newBuilder().setFirstName(name).build();
            requestObserver.onNext(GreetEveryoneRequest.newBuilder().setGreeting(greeting).build());
            try {
                Thread.sleep(20000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });


        requestObserver.onCompleted();


        try {
            latch.await(3L, TimeUnit.SECONDS);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        System.out.println("Hello I'm gRPC client");
        GreetingClient main = new GreetingClient();
        main.run();

        System.out.println("-------Shutting down channel-------");
    }
}
