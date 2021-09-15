package org.csystem.application.server.randompasswordgenerator.configuration.client;

import org.csystem.application.server.randompasswordgenerator.client.ClientInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class ClientInfoConfig {
    @Bean
    @Scope("prototype")
    public Map<Socket, ClientInfo> getRandomServerClientMap()
    {
        return new ConcurrentHashMap<>();
    }
}
