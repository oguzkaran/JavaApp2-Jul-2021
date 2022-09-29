package org.csystem.app.config;

import org.csystem.app.api.dto.OrderInfoDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ListConfiguration {
    @Bean
    @Scope("prototype")
    public List<OrderInfoDTO> createOrderInfoDTOList()
    {
        return new ArrayList<>();
    }
}
