package org.csystem.app.service.rest.greeting.service;

import org.csystem.app.service.rest.greeting.client.grpc.GreetingServiceGRPClientAppService;
import org.csystem.app.service.rest.greeting.dto.GreetingResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class GreetingAppService {
    private final GreetingServiceGRPClientAppService m_greetingServiceGRPClientAppService;

    public GreetingAppService(GreetingServiceGRPClientAppService greetingServiceGRPClientAppService)
    {
        m_greetingServiceGRPClientAppService = greetingServiceGRPClientAppService;
    }

    public GreetingResponseDTO receiveMessageTR(String firstName, String lastName, boolean married)
    {
        return m_greetingServiceGRPClientAppService.receiveMessageTR(firstName, lastName, married);
    }

    public GreetingResponseDTO receiveMessageEN(String firstName, String lastName, boolean married)
    {
        return m_greetingServiceGRPClientAppService.receiveMessageEN(firstName, lastName, married);
    }
}
