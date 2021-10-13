package org.csystem.application.udp.receiver.runner;

import org.csystem.application.udp.receiver.component.Receiver;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Component
public class ReceiverRunner implements ApplicationRunner {
    private final Receiver m_receiver;
    private final ExecutorService m_singleThread;

    public ReceiverRunner(Receiver receiver, ExecutorService singleThread)
    {
        m_receiver = receiver;
        m_singleThread = singleThread;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        m_singleThread.execute(m_receiver::run);
    }
}
