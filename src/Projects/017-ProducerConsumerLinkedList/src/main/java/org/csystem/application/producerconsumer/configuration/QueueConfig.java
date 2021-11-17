package org.csystem.application.producerconsumer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Deque;
import java.util.LinkedList;

@Configuration
public class QueueConfig {
    @Bean
    public Deque<Integer> getQueue()
    {
        return new LinkedList<>();
    }
}
