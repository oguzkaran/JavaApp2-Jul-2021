package org.csystem.application.server.randompasswordgenerator.configuration.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.ServerSocket;

@Configuration
public class ServerSocketConfig {
    @Value("${socket.randomServer.basePort}")
    private int m_basePort;

    @Value("${socket.randomServer.backlog}")
    private int m_backlog;

    @Bean("randomPasswordServerSocket")
    public ServerSocket getServerSocket() throws IOException
    {
        return new ServerSocket(m_basePort + 1, m_backlog);
    }

    @Bean("randomPasswordServerJavaSocket")
    public ServerSocket getServerJavaSocket() throws IOException
    {
        return new ServerSocket(m_basePort, m_backlog);
    }
}
