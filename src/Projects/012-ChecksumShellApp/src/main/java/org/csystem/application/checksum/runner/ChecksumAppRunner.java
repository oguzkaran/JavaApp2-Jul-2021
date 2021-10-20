package org.csystem.application.checksum.runner;

import org.csystem.application.checksum.commandprompt.Commands;
import org.csystem.util.commandprompt.CommandPrompt;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Component
public class ChecksumAppRunner implements ApplicationRunner {
    private final Commands m_commands;
    private final ExecutorService m_executorService;

    public ChecksumAppRunner(Commands commands, ExecutorService executorService)
    {
        m_commands = commands;
        m_executorService = executorService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        m_executorService.execute(() -> new CommandPrompt().setPrompt("checksum").register(m_commands).run());
        m_executorService.shutdown();
    }
}
