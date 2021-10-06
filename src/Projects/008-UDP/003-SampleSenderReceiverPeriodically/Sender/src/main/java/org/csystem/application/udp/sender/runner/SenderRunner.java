package org.csystem.application.udp.sender.runner;

import org.csystem.application.udp.sender.component.Sender;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Component
public class SenderRunner implements ApplicationRunner {
    private final Sender m_sender;
    private final ExecutorService m_singleThread;

    public SenderRunner(Sender sender, ExecutorService singleThread)
    {
        m_sender = sender;
        m_singleThread = singleThread;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        m_singleThread.execute(m_sender::run);
        m_singleThread.shutdown();
    }
}
