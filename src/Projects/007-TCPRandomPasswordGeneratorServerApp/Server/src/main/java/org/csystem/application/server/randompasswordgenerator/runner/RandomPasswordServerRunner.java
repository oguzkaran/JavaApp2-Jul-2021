package org.csystem.application.server.randompasswordgenerator.runner;

import org.csystem.util.console.Console;
import org.csystem.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;

import static org.csystem.util.exception.ExceptionUtil.subscribeRunnable;

@Component
public class RandomPasswordServerRunner implements ApplicationRunner {
    private final ServerSocket m_serverSocket;
    private final ExecutorService m_threadPool;

    @Value("${password.maxlength}")
    private int m_passwordMaxLength;

    @Value("${password.maxcount}")
    private int m_passwordMaxCount;

    private void handleClient(Socket clientSocket)
    {
        m_threadPool.execute(() -> generatePasswords(clientSocket));
    }

    private void send(int count, int length, BufferedWriter bw, DataOutputStream dos) throws IOException
    {
        boolean status = count > 0 && count <= m_passwordMaxCount && length > 0 && length <= m_passwordMaxLength;

        dos.writeBoolean(status);

        if (status)
            sendPasswords(count, length, bw);
    }

    private void sendPasswords(int count, int length, BufferedWriter bw) throws IOException
    {
        var random = new Random();

        for (var i = 0; i < count; ++i) {
            var text = StringUtil.getRandomTextEN(random, length);

            Console.writeLine("%s ", text);
            bw.write(text + "\r\n");
            bw.flush();
        }
    }

    private void generatePasswordsCallback(Socket clientSocket) throws IOException
    {
        var dis = new DataInputStream(clientSocket.getInputStream());
        var dos = new DataOutputStream(clientSocket.getOutputStream());
        var bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

        //Bu noktada client göndermezse ne olacak? Bu duruma karşı önlem almalıyız
        int count = dis.readInt();
        int length = dis.readInt();

        send(count, length, bw, dos);
    }

    private void generatePasswords(Socket clientSocket)
    {
        Console.writeLine("Host: %s, Port: %d, Local Port: %d%n", clientSocket.getInetAddress().getHostAddress(),
                clientSocket.getPort(), clientSocket.getLocalPort());

        subscribeRunnable(() -> generatePasswordsCallback(clientSocket), clientSocket,
                ex -> Console.Error.writeLine("generatePasswords:%s", ex.getMessage()));
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
