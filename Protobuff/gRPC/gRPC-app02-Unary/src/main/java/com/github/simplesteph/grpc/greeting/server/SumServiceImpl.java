package com.github.simplesteph.grpc.greeting.server;

import com.proto.sum.Data;
import com.proto.sum.DataRequest;
import com.proto.sum.DataResponse;
import com.proto.sum.SumServiceGrpc;
import io.grpc.stub.StreamObserver;

public class SumServiceImpl extends SumServiceGrpc.SumServiceImplBase {

    @Override
    public void sum(DataRequest request, StreamObserver<DataResponse> responseObserver) {
        Data data = request.getData();
        int a = data.getA();
        int b = data.getB();
        int sum = a + b;
        DataResponse dataResponse = DataResponse.newBuilder().setResult(sum).build();
        responseObserver.onNext(dataResponse);
        responseObserver.onCompleted();
    }
}
