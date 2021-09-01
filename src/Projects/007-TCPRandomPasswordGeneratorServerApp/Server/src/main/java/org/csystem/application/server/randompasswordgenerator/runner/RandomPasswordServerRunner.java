package org.csystem.application.server.randompasswordgenerator.runner;

import org.csystem.util.console.Console;
import org.csystem.util.string.StringUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;

import static org.csystem.util.exception.ExceptionUtil.subscribeRunnable;

@Component
public class RandomPasswordServerRunner implements ApplicationRunner {
    private final ServerSocket m_serverSocket;
    private final ExecutorService m_threadPool;

    private void handleClient(Socket clientSocket)
    {
        m_threadPool.execute(() -> generatePasswords(clientSocket));
    }

    private void sendPasswords(long count, int nChars, BufferedWriter bw) throws IOException
    {
        var random = new Random();

        for (var i = 0L; i < count; ++i) {
            var text = StringUtil.getRandomTextEN(random, nChars);

            Console.writeLine("%s ", text);
            bw.write(text + "\r\n");
            bw.flush();
        }
    }

    private void generatePasswords(Socket clientSocket)
    {
        Console.writeLine("Host: %s, Port: %d, Local Port: %d%n", clientSocket.getInetAddress().getHostAddress(),
                clientSocket.getPort(), clientSocket.getLocalPort());

        try (clientSocket) {
            var dis = new DataInputStream(clientSocket.getInputStream());
            var bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            //Bu noktada client göndermezse ne olacak? Bu duruma karşı önlem almalıyız
            //Burada ayrıca belleği etkin kullanmak için çeşitli işlemler yapılacak
            long count = dis.readLong();
            int nChars = dis.readInt();

            sendPasswords(count, nChars, bw);
        }
        catch (Throwable ex) {
           Console.Error.writeLine("generatePasswords:%s", ex.getMessage());
        }
    }

    private void acceptClient() throws IOException
    {
        Console.writeLine("Random Password Generator Server is waiting for a client");

        handleClient(m_serverSocket.accept());
    }

    private void runForAccept()
    {
        for (;;)
            subscribeRunnable(this::acceptClient, ex -> Console.Error.writeLine(ex.getMessage()));
    }

    private void runServerCallback()
    {
        runForAccept();
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
