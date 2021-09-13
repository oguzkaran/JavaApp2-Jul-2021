package org.csystem.application.server.randompasswordgenerator.configuration.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorConfig {
    //Cached thread pool soruna yol açabilir
    @Bean("executorService.cached")
    public ExecutorService getCachedThreadPool()
    {
        return Executors.newCachedThreadPool();
    }
    //...
}
