package org.csystem.application.server.randompasswordgenerator.configuration.async;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorConfig {
    @Bean
    @Scope("prototype")
    public ExecutorService getRandomServerExecutorService(@Value("${client.maxThread}") int maxThread)
    {
        return Executors.newFixedThreadPool(maxThread);
    }

    //...
}
