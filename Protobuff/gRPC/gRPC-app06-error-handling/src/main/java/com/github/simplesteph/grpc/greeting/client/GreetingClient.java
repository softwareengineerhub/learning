package com.github.simplesteph.grpc.greeting.client;

import com.proto.greet.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.ClientResponseObserver;
import io.grpc.stub.StreamObserver;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class GreetingClient {


    private void run() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext().build();
        doErrorCall(channel);
    }

    private void doErrorCall(ManagedChannel channel) {

        GreetServiceGrpc.GreetServiceBlockingStub blockingStub = GreetServiceGrpc.newBlockingStub(channel);

        CountDownLatch latch = new CountDownLatch(1);
        try {
            SquareRootResponse squareRootResponse = blockingStub.squareRoot(SquareRootRequest.newBuilder().setNumber(9).build());
            double res = squareRootResponse.getNumberRoot();
            System.out.println("Result: " + res);
        } catch (StatusRuntimeException ex) {
            System.out.println("Got an exception for square root!");
            ex.printStackTrace();
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
