package org.csystem.application.server.randompasswordgenerator.configuration.order;

import org.csystem.application.server.randompasswordgenerator.data.entity.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class OrdersConfig {

    @Bean
    public Map<Socket, Order> getOrderMap() {
        return new ConcurrentHashMap<>();
    }
}
