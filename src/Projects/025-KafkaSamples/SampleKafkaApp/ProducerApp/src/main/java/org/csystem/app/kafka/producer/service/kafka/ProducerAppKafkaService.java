package org.csystem.app.kafka.producer.service.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListenerAnnotationBeanPostProcessor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class ProducerAppKafkaService {
    private final KafkaTemplate<String, String> m_kafkaTemplate;

    @Value("${spring.kafka.producer.topic}")
    private String m_topic;

    private int thenApplyCallback(SendResult<String, String> sr)
    {
        System.out.println("-----------------------");
        System.out.printf("Offset:%d%n", sr.getRecordMetadata().offset());
        System.out.printf("Partition:%d%n", sr.getRecordMetadata().partition());
        System.out.printf("Topic:%s%n", sr.getRecordMetadata().topic());
        System.out.println("-----------------------");

        return 0;
    }

    public ProducerAppKafkaService(KafkaTemplate<String, String> kafkaTemplate)
    {
        m_kafkaTemplate = kafkaTemplate;
    }

    public void senMessage(String message)
    {
        var future = m_kafkaTemplate.send(m_topic, message);

        future.thenApplyAsync(this::thenApplyCallback);
    }
}
