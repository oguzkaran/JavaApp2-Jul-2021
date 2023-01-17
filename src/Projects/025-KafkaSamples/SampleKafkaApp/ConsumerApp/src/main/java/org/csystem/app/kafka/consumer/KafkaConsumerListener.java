package org.csystem.app.kafka.consumer;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {
    @KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "${spring.kafka.group-id}")
    public void listen(String message)
    {
        System.out.printf("Received: %s%n", message);
    }
}
