package org.csystem.application.udp.receiver.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorServiceConfig {
    @Bean
    public ExecutorService getFixedThreadExecutor(@Value("${receiver.nThreads}")int nThreads)
    {
        return Executors.newFixedThreadPool(nThreads);
    }
}
