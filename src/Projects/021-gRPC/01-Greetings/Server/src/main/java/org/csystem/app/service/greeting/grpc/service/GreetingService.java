package org.csystem.app.service.greeting.grpc.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.csystem.app.service.grpc.greeting.proto.GreetingRequest;
import org.csystem.app.service.grpc.greeting.proto.GreetingResponse;
import org.csystem.app.service.grpc.greeting.proto.GreetingServiceGrpc;

@GrpcService
public class GreetingService extends GreetingServiceGrpc.GreetingServiceImplBase {
    @Override
    public void helloEN(GreetingRequest request, StreamObserver<GreetingResponse> responseObserver)
    {
        var response = GreetingResponse.newBuilder()
                .setGreeting("Hi " + request.getFirstName() + " " + request.getLastName())
                .setMaritalStatus("You are " + (request.getMarried() ? "married" : "single"))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void helloTR(GreetingRequest request, StreamObserver<GreetingResponse> responseObserver)
    {
        var response = GreetingResponse.newBuilder()
                .setGreeting("Merhaba " + request.getFirstName() + " " + request.getLastName())
                .setMaritalStatus(request.getMarried() ? "Evli" : "Bekar")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
