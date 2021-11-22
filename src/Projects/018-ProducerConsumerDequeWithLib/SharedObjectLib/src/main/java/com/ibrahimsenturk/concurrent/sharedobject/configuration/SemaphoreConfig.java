package com.ibrahimsenturk.concurrent.sharedobject.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Semaphore;

@Configuration
public class SemaphoreConfig {
    @Bean("com.ibrahimsenturk.concurrent.sharedobject.producerSemaphore")
    public Semaphore getProducerSemaphore(@Value("${queue.size}") int permits)
    {
        return new Semaphore(permits, true);
    }

    @Bean("com.ibrahimsenturk.concurrent.sharedobject.consumerSemaphore")
    public Semaphore getConsumerSemaphore()
    {
        return new Semaphore(0);
    }
}
