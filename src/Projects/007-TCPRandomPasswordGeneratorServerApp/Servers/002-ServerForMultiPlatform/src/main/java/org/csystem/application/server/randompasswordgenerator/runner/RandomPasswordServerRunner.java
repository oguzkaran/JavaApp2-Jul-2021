package org.csystem.application.server.randompasswordgenerator.runner;

import org.csystem.application.server.randompasswordgenerator.client.ClientInfo;
import org.csystem.util.console.Console;
import org.csystem.util.net.TcpUtil;
import org.csystem.util.scheduler.Scheduler;
import org.csystem.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static org.csystem.util.exception.ExceptionUtil.subscribeRunnable;

@Component
public class RandomPasswordServerRunner implements ApplicationRunner {
    private final ServerSocket m_serverSocket;
    private final ExecutorService m_threadPool;
    private final Map<Socket, ClientInfo> m_clients;

    @Value("${password.maxlength}")
    private int m_passwordMaxLength;

    @Value("${password.maxcount}")
    private int m_passwordMaxCount;

    @Value("${scheduler.interval}")
    private int m_schedulerInterval;

    @Value("${scheduler.intervalUnitStr}")
    private String m_schedulerIntervalUnitStr;

    @Value("${scheduler.threshold}")
    private int m_schedulerThreshold;

    @Value("${scheduler.thresholdUnitStr}")
    private String m_schedulerThresholdUnitStr;

    private void handleClient(Socket clientSocket)
    {
        m_threadPool.execute(() -> generatePasswords(clientSocket));
    }

    private void send(Socket socket, int count, int length) throws IOException
    {
        boolean status = count > 0 && count <= m_passwordMaxCount && length > 0 && length <= m_passwordMaxLength;

        TcpUtil.sendInt(socket, status ? 1 : 0);

        if (status)
            sendPasswords(socket, count, length);

        m_clients.remove(socket); // Bu işlem exception oluşursa da yapılacaktır
    }

    private void sendPasswords(Socket socket, int count, int length) throws IOException
    {
        var random = new Random();

        for (var i = 0; i < count; ++i) {
            var text = StringUtil.getRandomTextEN(random, length);

            Console.writeLine("%s ", text);
            TcpUtil.sendString(socket, text);
        }
    }

    private void clientsSynchronize(Runnable runnable)
    {
        synchronized (m_clients) {
            runnable.run();
        }
    }

    private void setLastTimeCallback(ClientInfo clientInfo)
    {
        if (!m_clients.containsKey(clientInfo.getSocket()))
            return;

        clientInfo.setLastTime(LocalDateTime.now());
    }

    private void setCompletedCallback(ClientInfo clientInfo)
    {
        synchronized (m_clients) {
            if (!m_clients.containsKey(clientInfo.getSocket()))
                return;

            clientInfo.setCompleted();
        }
    }

    private void generatePasswordsCallback(Socket clientSocket) throws IOException
    {
        var clientInfo = new ClientInfo(clientSocket, clientSocket.getPort());
        m_clients.put(clientSocket, clientInfo);

        int count = TcpUtil.receiveInt(clientSocket);

        clientsSynchronize(() -> setLastTimeCallback(clientInfo));

        int length = TcpUtil.receiveInt(clientSocket);

        clientsSynchronize(() -> setCompletedCallback(clientInfo));

        send(clientSocket, count, length);
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

    private boolean isRemovable(Socket socket, int threshold, String schedulerThresholdUnitStr)
    {
        var now = LocalDateTime.now();
        var ci = m_clients.get(socket);

        var status = ChronoUnit.valueOf(schedulerThresholdUnitStr).between(ci.getLastTime(), now) > threshold;

        subscribeRunnable(() -> {if (status) socket.close();}, ex -> Console.Error.writeLine(ex.getMessage()));

        return status;
    }

    private void schedulerSynchronizedCallback()
    {
        m_clients.keySet().removeIf(s -> isRemovable(s, m_schedulerThreshold, m_schedulerThresholdUnitStr));
    }

    private void schedulerCallback()
    {
        Console.writeLine("Client Size:%d", m_clients.size());
        clientsSynchronize(this::schedulerSynchronizedCallback);
    }

    public RandomPasswordServerRunner(ServerSocket serverSocket, ExecutorService threadPool, Map<Socket, ClientInfo> clients)
    {
        m_serverSocket = serverSocket;
        m_threadPool = threadPool;
        m_clients = clients;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        new Scheduler(m_schedulerInterval, TimeUnit.valueOf(m_schedulerIntervalUnitStr)).schedule(this::schedulerCallback);
        m_threadPool.execute(this::runServerCallback);
    }
}
