package com.github.simplesteph.grpc.greeting.server;

import com.proto.greet.*;
import io.grpc.stub.StreamObserver;

public class GreetServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {

    @Override
    public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {
        System.out.println("####Server.greet()####");
        String firstName = request.getGreeting().getFirstName();
        System.out.println("####Server.greet()####fisrtName=" + firstName);
        GreetResponse response = GreetResponse.newBuilder().setResult("Hello, " + firstName).build();
        System.out.println("####Server.greet()####sendResponse()");
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        System.out.println("####Server.greet()####completed");
    }

    @Override
    public void greetManyTimes(GreetManyTimesRequest request, StreamObserver<GreetManyTimesResponse> responseObserver) {
        String firstName = request.getGreeting().getFirstName();

        for (int i = 0; i < 100; i++) {
            String result = "Hello " + firstName + ", response number: " + i;
            GreetManyTimesResponse response = GreetManyTimesResponse.newBuilder().setResult(result).build();
            responseObserver.onNext(response);
            try {
                Thread.sleep(1000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        responseObserver.onCompleted();
    }


    @Override
    public StreamObserver<LongGreetRequest> longGreet(StreamObserver<LongGreetResponse> responseObserver) {
        StreamObserver<LongGreetRequest> requestObserver = new StreamObserver<LongGreetRequest>() {

            String result = "";

            @Override
            public void onNext(LongGreetRequest value) {
                System.out.println("Server received: " +value.getGreeting().getFirstName());
                result+="Hello "+value.getGreeting().getFirstName()+"! ";
            }

            @Override
            public void onError(Throwable t) {
                //client send an error
            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(LongGreetResponse.newBuilder().setResult(result).build());

                //client is done
                //this is when we want to return response
                responseObserver.onCompleted();
            }
        };
        return requestObserver;
    }
}
