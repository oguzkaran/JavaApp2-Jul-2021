package org.csystem.app.service.rest.greeting.client.grpc;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.csystem.app.service.grpc.greeting.proto.GreetingRequest;
import org.csystem.app.service.grpc.greeting.proto.GreetingServiceGrpc;
import org.csystem.app.service.rest.greeting.dto.GreetingResponseDTO;
import org.csystem.app.service.rest.greeting.mapper.IGreetingResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GreetingServiceGRPClientAppService {
    @GrpcClient("greetingService")
    private GreetingServiceGrpc.GreetingServiceBlockingStub m_greetingServiceBlockingStub;

    private final IGreetingResponseMapper m_greetingResponseMapper;

    public GreetingServiceGRPClientAppService(IGreetingResponseMapper greetingResponseMapper)
    {
        m_greetingResponseMapper = greetingResponseMapper;
    }

    public GreetingResponseDTO receiveMessageTR(String firstName, String lastName, boolean married)
    {
        var request = GreetingRequest.newBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setMarried(married)
                .build();

        //...

        return m_greetingResponseMapper.toGreetingResponseDTO(m_greetingServiceBlockingStub.helloTR(request));
    }

    public GreetingResponseDTO receiveMessageEN(String firstName, String lastName, boolean married)
    {
        var request = GreetingRequest.newBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setMarried(married)
                .build();

        //...

        return m_greetingResponseMapper.toGreetingResponseDTO(m_greetingServiceBlockingStub.helloEN(request));
    }

}
