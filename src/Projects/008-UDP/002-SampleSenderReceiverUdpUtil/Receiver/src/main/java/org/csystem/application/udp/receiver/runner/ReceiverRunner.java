package org.csystem.application.udp.receiver.runner;

import org.csystem.application.udp.receiver.component.Receiver;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ReceiverRunner implements ApplicationRunner {
    private final Receiver m_receiver;

    public ReceiverRunner(Receiver receiver)
    {
        m_receiver = receiver;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        m_receiver.run();
    }
}
