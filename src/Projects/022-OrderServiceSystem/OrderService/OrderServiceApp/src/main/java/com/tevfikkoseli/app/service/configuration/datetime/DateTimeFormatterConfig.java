package com.tevfikkoseli.app.service.configuration.datetime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
public class DateTimeFormatterConfig {
    @Bean("iso_local_date_formatter")
    public DateTimeFormatter getISOLocalDateFormatter()
    {
        return DateTimeFormatter.ISO_LOCAL_DATE;
    }
}
