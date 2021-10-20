package org.csystem.application.client.makeupper.runner;

import org.csystem.application.client.makeupper.client.Client;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Component
public class ClientRunner implements ApplicationRunner {
    private final Client m_client;
    private final ExecutorService m_executorService;

    public ClientRunner(Client client, ExecutorService executorService)
    {
        m_client = client;
        m_executorService = executorService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        m_executorService.execute(m_client::run);
        m_executorService.shutdown();
    }
}
