package org.csystem.app.kafka.consumer.configuratiıon;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
public class KafkaListenerConfig {
    private final ConsumerFactory<String, String> m_consumerFactory;

    public KafkaListenerConfig(ConsumerFactory<String, String> consumerFactory)
    {
        m_consumerFactory = consumerFactory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> getConcurrentKafkaListenerContainerFactory()
    {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();

        factory.setConsumerFactory(m_consumerFactory);

        return factory;
    }
}
