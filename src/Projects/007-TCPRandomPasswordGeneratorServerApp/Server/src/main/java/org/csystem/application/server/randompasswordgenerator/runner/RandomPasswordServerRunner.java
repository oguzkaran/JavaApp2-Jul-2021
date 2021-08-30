package org.csystem.application.server.randompasswordgenerator.runner;

import org.csystem.util.console.Console;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;

@Component
public class RandomPasswordServerRunner implements ApplicationRunner {
    private final ServerSocket m_serverSocket;
    private final ExecutorService m_threadPool;

    private void runServerCallback()
    {
        for (;;) {
            try {
                Console.writeLine("Random Password Generator Server is waiting for a client");
                var clientSocket = m_serverSocket.accept();

                Console.writeLine("Client connected with %s:%d%n", clientSocket.getInetAddress().getHostAddress(),
                        clientSocket.getPort());
            }
            catch (IOException ex) {
                Console.Error.writeLine(ex.getMessage());
            }
        }
    }

    public RandomPasswordServerRunner(ServerSocket serverSocket, ExecutorService threadPool)
    {
        m_serverSocket = serverSocket;
        m_threadPool = threadPool;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        m_threadPool.execute(this::runServerCallback);
    }
}
