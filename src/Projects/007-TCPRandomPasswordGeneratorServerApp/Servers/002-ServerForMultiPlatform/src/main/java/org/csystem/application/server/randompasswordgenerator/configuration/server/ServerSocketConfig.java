package org.csystem.application.server.randompasswordgenerator.configuration.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.ServerSocket;

@Configuration
public class ServerSocketConfig {
    @Value("${socket.port:50500}")
    private int m_port;

    @Value("${socket.backlog:50}")
    private int m_backlog;

    @Bean
    public ServerSocket getServerSocket() throws IOException
    {
        return new ServerSocket(m_port, m_backlog);
    }
}
