package org.csystem.application.server.randompasswordgenerator.runner;

import org.csystem.application.server.randompasswordgenerator.component.Sender;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Component
public class SendPortRunner implements ApplicationRunner {    private final ExecutorService m_singleThread;
    private final Sender m_sender;

    public SendPortRunner(@Qualifier("executorService.single") ExecutorService singleThread, Sender sender)
    {
        m_singleThread = singleThread;
        m_sender = sender;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        m_singleThread.execute(m_sender::run);
        m_singleThread.shutdown();
    }
}
