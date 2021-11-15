package org.csystem.application.producerconsumer.configuration;

import org.csystem.collection.CircularQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {
    @Bean
    public CircularQueue<Integer> getQueue(@Value("${queue.size}") int size)
    {
        return new CircularQueue<>(size);
    }
}
