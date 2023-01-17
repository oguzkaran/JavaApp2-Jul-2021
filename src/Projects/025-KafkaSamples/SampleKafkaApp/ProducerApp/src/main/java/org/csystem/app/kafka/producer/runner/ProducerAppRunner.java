package org.csystem.app.kafka.producer.runner;

import org.csystem.app.kafka.producer.service.kafka.ProducerAppKafkaService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Component
public class ProducerAppRunner implements ApplicationRunner {
    private final ExecutorService m_executorService;
    private final ProducerAppKafkaService m_producerAppKafkaService;


    private void schedulerCallback()
    {
        for (int i = 0; i < 10; ++i)
            m_producerAppKafkaService.senMessage("Message-" + i);
    }

    public ProducerAppRunner(ExecutorService executorService, ProducerAppKafkaService producerAppKafkaService)
    {
        m_executorService = executorService;
        m_producerAppKafkaService = producerAppKafkaService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        m_executorService.execute(this::schedulerCallback);
    }
}
