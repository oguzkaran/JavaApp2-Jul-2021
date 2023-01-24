package org.csystem.app.kafka.consumer.listener;

import org.csystem.app.kafka.consumer.data.mongodb.entity.TextInfo;
import org.csystem.app.kafka.consumer.data.mongodb.repository.ITextInfoRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {
    private final ITextInfoRepository m_textInfoRepository;

    private void writeMongo(String message)
    {
        var ti = new TextInfo(message, "producer", "");

        m_textInfoRepository.save(ti);
    }

    public KafkaConsumerListener(ITextInfoRepository textInfoRepository)
    {
        m_textInfoRepository = textInfoRepository;
    }

    @KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "${spring.kafka.group-id}")
    public void listen(String message)
    {
        System.out.printf("Received: %s%n", message);
        writeMongo(message);
    }
}
