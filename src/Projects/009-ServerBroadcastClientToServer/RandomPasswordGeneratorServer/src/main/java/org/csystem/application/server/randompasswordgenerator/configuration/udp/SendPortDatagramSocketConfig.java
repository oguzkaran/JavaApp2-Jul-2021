package org.csystem.application.server.randompasswordgenerator.configuration.udp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.DatagramSocket;
import java.net.SocketException;

@Configuration
public class SendPortDatagramSocketConfig {
    @Bean
    public DatagramSocket getDatagramSocket() throws SocketException
    {
        return new DatagramSocket();
    }
}
