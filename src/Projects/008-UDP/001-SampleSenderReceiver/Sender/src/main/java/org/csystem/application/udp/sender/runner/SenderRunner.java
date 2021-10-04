package org.csystem.application.udp.sender.runner;

import org.csystem.application.udp.sender.component.Sender;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SenderRunner implements ApplicationRunner {
    private final Sender m_sender;

    public SenderRunner(Sender sender)
    {
        m_sender = sender;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        m_sender.run();
    }
}
