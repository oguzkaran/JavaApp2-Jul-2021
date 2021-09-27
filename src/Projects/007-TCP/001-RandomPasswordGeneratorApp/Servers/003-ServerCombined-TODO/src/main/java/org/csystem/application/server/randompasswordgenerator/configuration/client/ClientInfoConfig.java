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
    @Bean("randomPasswordServerJavaClientsMap")
    @Scope("prototype")
    public Map<Socket, ClientInfo> getRandomServerJavaClientsMap()
    {
        return new ConcurrentHashMap<>();
    }

    @Bean("randomPasswordServerClientsMap")
    @Scope("prototype")
    public Map<Socket, ClientInfo> getRandomServerClientsMap()
    {
        return new ConcurrentHashMap<>();
    }
}
