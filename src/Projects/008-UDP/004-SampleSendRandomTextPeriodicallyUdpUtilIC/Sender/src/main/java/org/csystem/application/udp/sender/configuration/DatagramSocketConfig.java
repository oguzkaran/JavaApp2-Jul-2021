package org.csystem.application.udp.sender.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.DatagramSocket;
import java.net.SocketException;

@Configuration
public class DatagramSocketConfig {
    @Bean
    public DatagramSocket getDatagramSocket() throws SocketException
    {
        return new DatagramSocket();
    }
}
