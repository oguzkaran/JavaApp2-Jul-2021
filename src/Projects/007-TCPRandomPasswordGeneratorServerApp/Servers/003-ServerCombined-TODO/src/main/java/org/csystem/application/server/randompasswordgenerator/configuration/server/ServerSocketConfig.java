package org.csystem.application.server.randompasswordgenerator.configuration.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.ServerSocket;

@Configuration
public class ServerSocketConfig {
    @Value("${socket.randomServer.port}")
    private int m_port;

    @Value("${socket.randomServer.backlog}")
    private int m_backlog;

    @Bean("randomServerSocket")
    public ServerSocket getServerSocket() throws IOException
    {
        return new ServerSocket(m_port, m_backlog);
    }
}
