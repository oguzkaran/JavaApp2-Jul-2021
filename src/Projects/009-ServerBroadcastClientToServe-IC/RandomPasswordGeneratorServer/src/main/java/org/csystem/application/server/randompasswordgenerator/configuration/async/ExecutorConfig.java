package org.csystem.application.server.randompasswordgenerator.configuration.async;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorConfig {
    @Bean("executorService.fixed")
    @Primary
    public ExecutorService getFixedThreadPool(@Value("${client.maxThread}") int maxThread)
    {
        return Executors.newFixedThreadPool(maxThread);
    }

    @Bean("executorService.single")
    public ExecutorService getSingleThreadExecutor()
    {
        return Executors.newSingleThreadExecutor();
    }
    //...
}
