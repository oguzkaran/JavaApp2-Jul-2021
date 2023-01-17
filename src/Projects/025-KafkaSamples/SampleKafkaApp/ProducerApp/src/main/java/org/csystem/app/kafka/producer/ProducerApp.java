package org.csystem.app.kafka.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class ProducerApp {
    public static void main(String[] args)
    {
        SpringApplication.run(ProducerApp.class, args);
    }
}
