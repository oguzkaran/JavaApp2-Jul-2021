package org.csystem.application.client.console.general.helper;

import org.csystem.application.client.console.general.runner.RandomPasswordClient;
import org.csystem.application.client.console.general.runner.RandomPasswordClientJava;
import org.csystem.application.client.console.general.runner.RandomPasswordClientStayConnected;
import org.csystem.application.client.console.general.runner.RandomPasswordFileClient;
import org.springframework.stereotype.Component;

@Component
public class RandomPasswordHelper {
    private final RandomPasswordClientJava m_clientJava;
    private final RandomPasswordClient m_client;
    private final RandomPasswordClientStayConnected m_clientStayConnected;
    private final RandomPasswordFileClient m_clientFile;

    public RandomPasswordHelper(RandomPasswordClientJava clientJava, RandomPasswordClient client,
                                RandomPasswordClientStayConnected clientStayConnected, RandomPasswordFileClient clientFile)
    {
        m_clientJava = clientJava;
        m_client = client;
        m_clientStayConnected = clientStayConnected;
        m_clientFile = clientFile;
    }

    public void runClientJava() throws Exception
    {
        m_clientJava.run();
    }

    public void runClient() throws Exception
    {
        m_client.run();
    }

    public void runClientStayConnected() throws Exception
    {
        m_clientStayConnected.run();
    }

    public void runClientFile() throws Exception
    {
        m_clientFile.run();
    }
}
