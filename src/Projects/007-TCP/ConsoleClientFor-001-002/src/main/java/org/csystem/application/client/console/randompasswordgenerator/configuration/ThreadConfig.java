package org.csystem.application.client.console.randompasswordgenerator.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ThreadConfig {
    @Bean
    public ExecutorService getSingleThreadExecutor()
    {
        return Executors.newSingleThreadExecutor();
    }
}
