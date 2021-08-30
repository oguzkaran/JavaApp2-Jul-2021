package org.csystem.application.server.randompasswordgenerator.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorConfig {
    @Bean("executorService.cached")
    public ExecutorService getCachedThreadPool()
    {
        return Executors.newCachedThreadPool();
    }
    //...
}
