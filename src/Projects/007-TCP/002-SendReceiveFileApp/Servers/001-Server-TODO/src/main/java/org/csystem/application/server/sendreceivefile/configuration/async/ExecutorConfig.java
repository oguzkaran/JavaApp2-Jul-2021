package org.csystem.application.server.sendreceivefile.configuration.async;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorConfig {
    @Bean("executorService.cached")
    public ExecutorService getCachedThreadPool(@Value("${client.maxThread}") int maxThread)
    {
        return Executors.newFixedThreadPool(maxThread);
    }
    //...
}
