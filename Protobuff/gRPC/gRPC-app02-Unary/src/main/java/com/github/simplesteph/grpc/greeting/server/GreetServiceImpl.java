package com.github.simplesteph.grpc.greeting.server;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import io.grpc.stub.StreamObserver;

public class GreetServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {

    @Override
    public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {
        System.out.println("####Server.greet()####");
        String firstName = request.getGreeting().getFirstName();
        System.out.println("####Server.greet()####fisrtName="+firstName);
        GreetResponse response = GreetResponse.newBuilder().setResult("Hello, " + firstName).build();
        System.out.println("####Server.greet()####sendResponse()");
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        System.out.println("####Server.greet()####completed");
    }
}
