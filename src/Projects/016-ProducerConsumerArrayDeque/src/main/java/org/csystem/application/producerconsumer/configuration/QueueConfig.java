package org.csystem.application.producerconsumer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayDeque;
import java.util.Deque;

@Configuration
public class QueueConfig {
    @Bean
    public Deque<Integer> getQueue()
    {
        return new ArrayDeque<>();
    }
}
