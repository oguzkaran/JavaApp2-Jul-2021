package org.csystem.app.kafka.producer.configuration;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;

@Configuration
public class KafkaProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String m_kafkaBootstrapServer;

    @Bean
    public ProducerFactory<String, String> getProducerFactory()
    {
        var factoryProps = new HashMap<String, Object>();

        factoryProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, m_kafkaBootstrapServer);
        factoryProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        factoryProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        factoryProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        factoryProps.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "csd-producer-1");

        return new DefaultKafkaProducerFactory<>(factoryProps);
    }
}
