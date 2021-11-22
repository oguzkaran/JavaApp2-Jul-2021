package org.csystem.application.producerconsumer.configuration;

import com.ibrahimsenturk.concurrent.sharedobject.global.SharedObjectBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

@Configuration
public class QueueConfig {
    @Bean(SharedObjectBean.DEQUE_BEAN)
    public Deque<Integer> getQueue()
    {
        return new ArrayDeque<>();
    }
}
