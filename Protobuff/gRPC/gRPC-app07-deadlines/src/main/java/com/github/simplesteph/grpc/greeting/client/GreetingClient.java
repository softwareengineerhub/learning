package com.github.simplesteph.grpc.greeting.client;

import com.proto.greet.*;
import io.grpc.*;
import io.grpc.stub.ClientResponseObserver;
import io.grpc.stub.StreamObserver;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class GreetingClient {


    private void run() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext().build();
        doCallWithDeadline(channel);
    }

    private void doCallWithDeadline(ManagedChannel channel) {

        GreetServiceGrpc.GreetServiceBlockingStub blockingStub = GreetServiceGrpc.newBlockingStub(channel);

        CountDownLatch latch = new CountDownLatch(1);
        try {
            Greeting greeting = Greeting.newBuilder().setFirstName("Test").build();
            //GreetWithDeadlineResponse response = blockingStub.greetWithDeadline(GreetWithDeadlineRequest.newBuilder().setGreeting(greeting).build());
            GreetWithDeadlineResponse response = blockingStub.withDeadline(Deadline.after(500, TimeUnit.MILLISECONDS))
                    .greetWithDeadline(GreetWithDeadlineRequest.newBuilder().setGreeting(greeting).build());
            String res = response.getResult();
            System.out.println("Result: " + res);
        } catch (StatusRuntimeException ex) {
            if (ex.getStatus() == Status.DEADLINE_EXCEEDED) {
                System.out.println("Deadline has been exceeded, we don't want the response");
            } else {
                ex.printStackTrace();
            }

        }

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
