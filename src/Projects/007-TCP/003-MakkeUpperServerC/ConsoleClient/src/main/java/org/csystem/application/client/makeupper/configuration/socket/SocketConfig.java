package org.csystem.application.client.makeupper.configuration.socket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.Socket;

@Configuration
public class SocketConfig {
    @Bean
    public Socket getSocket(@Value("${client.host}") String host, @Value("${client.port}") int port) throws IOException
    {
        return new Socket(host, port);
    }
}
