package com.github.simplesteph.grpc.greeting.client;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import com.proto.sum.Data;
import com.proto.sum.DataRequest;
import com.proto.sum.DataResponse;
import com.proto.sum.SumServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class SumClient {

    public static void main(String[] args) {
        System.out.println("Hello I'm gRPC client");

        //usePlaintext() - disbales SSL
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext().build();

        System.out.println("Creating Stub");

        SumServiceGrpc.SumServiceBlockingStub sumClient = SumServiceGrpc.newBlockingStub(channel);

        //do something
        Data data = Data.newBuilder().setA(1).setB(2).build();
        DataRequest request = DataRequest.newBuilder().setData(data).build();
        DataResponse response = sumClient.sum(request);
        System.out.println("Sum: "+response.getResult());

        System.out.println("-------Shutting down channel-------");
        channel.shutdown();

    }
}
