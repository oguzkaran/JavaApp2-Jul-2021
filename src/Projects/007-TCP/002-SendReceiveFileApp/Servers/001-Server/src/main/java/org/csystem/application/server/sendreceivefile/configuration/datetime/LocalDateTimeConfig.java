package org.csystem.application.server.sendreceivefile.configuration.datetime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;

@Configuration
public class LocalDateTimeConfig {
    @Bean
    @Scope("prototype")
    public LocalDateTime now()
    {
        return LocalDateTime.now();
    }
}
