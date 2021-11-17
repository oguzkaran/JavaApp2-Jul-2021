package org.csystem.application.producerconsumer.configuration;

import org.csystem.collection.LinkedListQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {
    @Bean
    public LinkedListQueue<Integer> getQueue()
    {
        return new LinkedListQueue<>();
    }
}
