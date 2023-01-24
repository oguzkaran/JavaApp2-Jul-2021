package org.csystem.app.kafka.producer.runner;

import org.csystem.app.kafka.producer.service.kafka.ProducerAppKafkaService;
import org.csystem.util.string.StringUtil;
import org.csystem.util.thread.ThreadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.ExecutorService;

@Component
public class ProducerAppRunner implements ApplicationRunner {
    private final ExecutorService m_executorService;
    private final ProducerAppKafkaService m_producerAppKafkaService;

    private final Random m_random;

    @Value("${producer.name}")
    private String m_name;

    @Value("${spring.kafka.producer.topic}")
    private String m_topic;

    private void schedulerCallback()
    {
        for (;;) {
            var message = String.format("%s", StringUtil.getRandomTextEN(m_random, m_random.nextInt(4, 7)));
            m_producerAppKafkaService.senMessage(message);
            ThreadUtil.sleep(10);
        }
    }

    public ProducerAppRunner(ExecutorService executorService, ProducerAppKafkaService producerAppKafkaService, Random random)
    {
        m_executorService = executorService;
        m_producerAppKafkaService = producerAppKafkaService;
        m_random = random;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        m_executorService.execute(this::schedulerCallback);
    }
}
