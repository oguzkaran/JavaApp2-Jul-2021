package org.csystem.application.udp.receiver.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.DatagramSocket;
import java.net.SocketException;

@Configuration
public class DatagramSocketConfig {
    @Bean
    public DatagramSocket getDatagramSocket(@Value("${receiver.port}")int port) throws SocketException
    {
        return new DatagramSocket(port);
    }
}
