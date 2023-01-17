package org.csystem.app.kafka.producer.service.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerAppKafkaService {
    private final KafkaTemplate<String, String> m_kafkaTemplate;

    @Value("${spring.kafka.producer.topic}")
    private String m_topic;

    public ProducerAppKafkaService(KafkaTemplate<String, String> kafkaTemplate)
    {
        m_kafkaTemplate = kafkaTemplate;
    }

    public void senMessage(String message)
    {
        m_kafkaTemplate.send(m_topic, message);
    }
}
