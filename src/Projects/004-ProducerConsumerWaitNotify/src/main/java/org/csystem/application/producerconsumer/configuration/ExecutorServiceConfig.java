package org.csystem.application.producerconsumer.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.ExecutorService;
import static java.util.concurrent.Executors.newCachedThreadPool;
import static java.util.concurrent.Executors.newFixedThreadPool;

@Configuration
public class ExecutorServiceConfig {
    @Bean
    public ExecutorService getExecutorService(@Value("${thread.count:-1}") int count)
    {
        return count == -1 ? newCachedThreadPool() : newFixedThreadPool(count);
    }
}
