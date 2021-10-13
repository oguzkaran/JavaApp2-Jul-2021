package org.csystem.application.server.randompasswordgenerator.runner;

import org.csystem.application.server.randompasswordgenerator.tcp.RandomPasswordServer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Component
public class RandomPasswordServerRunner implements ApplicationRunner {
    private final ExecutorService m_threadPool;
    private final RandomPasswordServer m_randomPasswordServer;

    public RandomPasswordServerRunner(ExecutorService threadPool, RandomPasswordServer randomPasswordServer)
    {
        m_threadPool = threadPool;
        m_randomPasswordServer = randomPasswordServer;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        m_threadPool.execute(m_randomPasswordServer::run);
    }
}
