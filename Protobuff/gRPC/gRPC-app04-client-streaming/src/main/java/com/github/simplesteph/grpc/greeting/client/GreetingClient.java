package com.github.simplesteph.grpc.greeting.client;

import com.proto.greet.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class GreetingClient {


    private void run() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext().build();
        doClientStreamingCall(channel);
    }

    private void doClientStreamingCall(ManagedChannel channel) {
        GreetServiceGrpc.GreetServiceStub asyncClient = GreetServiceGrpc.newStub(channel);

        CountDownLatch latch = new CountDownLatch(1);

        StreamObserver<LongGreetRequest> requestObserver = asyncClient.longGreet(new StreamObserver<LongGreetResponse>() {
            @Override
            public void onNext(LongGreetResponse value) {
                // we get a response from the server
                System.out.println("Received a response from the server");
                System.out.println(value.getResult());
                //onNext will be called only once
            }

            @Override
            public void onError(Throwable t) {
                //we got an error from server
            }

            @Override
            public void onCompleted() {
                //the server is done sending us data
                System.out.println("Server has completed sending us something");
                latch.countDown();
            }
        });

        Greeting greeting1 = Greeting.newBuilder().setFirstName("Name1").build();
        LongGreetRequest request1 = LongGreetRequest.newBuilder().setGreeting(greeting1).build();
        System.out.println("Sending message1");
        requestObserver.onNext(request1);

        Greeting greeting2 = Greeting.newBuilder().setFirstName("Name2").build();
        LongGreetRequest request2 = LongGreetRequest.newBuilder().setGreeting(greeting2).build();
        System.out.println("Sending message2");
        requestObserver.onNext(request2);

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Greeting greeting3 = Greeting.newBuilder().setFirstName("Name3").build();
        LongGreetRequest request3 = LongGreetRequest.newBuilder().setGreeting(greeting3).build();
        System.out.println("Sending message3");
        requestObserver.onNext(request3);

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //we tell the server that client is end sending data
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
