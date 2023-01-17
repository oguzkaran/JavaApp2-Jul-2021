package org.csystem.app.kafka.consumer.configuratiıon;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;

@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String m_kafkaBootstrapServer;

    @Value("${spring.kafka.group-id}")
    private String m_groupId;

    @Bean
    public ConsumerFactory<String, String> getConsumerFactory()
    {
        var factoryProps = new HashMap<String, Object>();

        factoryProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, m_kafkaBootstrapServer);
        factoryProps.put(ConsumerConfig.GROUP_ID_CONFIG, m_groupId);
        factoryProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        factoryProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(factoryProps);
    }
}
