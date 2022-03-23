package org.csystem.application.server.randompasswordgenerator.runner;

import org.csystem.application.server.randompasswordgenerator.orderinfo.OrderInfo;
import org.csystem.util.console.Console;
import org.csystem.util.net.TcpUtil;
import org.csystem.util.scheduler.Scheduler;
import org.csystem.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.*;
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
public class OrderServiceServerRunner implements ApplicationRunner {

    private final ServerSocket m_serverSoket;
    private final ExecutorService m_treadPool;
    private final Map<Socket, OrderInfo> m_order;


    @Value("${password.maxlength}")
    private int m_passwordMaxLength;
    @Value("${password.maxcount}")
    private int m_passwordMaxCount;

    @Value("${scheduler.interval}")
    private int m_schedulerInterval;

    @Value(("${scheduler.intervalUnitStr}"))
    private String m_schedulerIntervalUnitStr;

    @Value(("${scheduler.threshold}"))
    private int m_schedulerThreshold;

    @Value(("${scheduler.thresholdUnitStr}"))
    private String m_schedulerThresholdUnitStr;

    private void handleClient(Socket orderSoket) {

            m_treadPool.execute(() -> generatePasswords(orderSoket));



    }

    private void  send(Socket socket, int count, int length) throws IOException {

        boolean status = count > 0 && count <= m_passwordMaxCount && length > 0 && length <= m_passwordMaxLength;

        TcpUtil.sendInt(socket, status ? 1 : 0);

        if (status)
            sendPassword(socket, count,length);

        m_order.remove(socket);

    }

    private void sendPassword(Socket soket ,int count, int length) throws IOException {

        var random = new Random();
        for (var i=0; i < count ; ++i) {
            var text = StringUtil.getRandomTextEN(random, length);

            Console.write("%s ", text);
            TcpUtil.sendString(soket, text);
        }
    }

    private void clientSynchronize(Runnable runnable) {
        synchronized (m_order) {
            runnable.run();
        }
    }

    private void setLastTimeCallback(OrderInfo orderInfo) {

        if (!m_order.containsKey(orderInfo.getSocket()))
            return;
        orderInfo.setLastTime(LocalDateTime.now());

    }

    private void setCompletedCallback(OrderInfo orderInfo) {

        if (!m_order.containsKey(orderInfo.getSocket() ))
            return;
        orderInfo.setCompleted();

    }



    private void generatePasswordsCallback(Socket clientSoket) throws IOException {



        var clientInfo = new OrderInfo(clientSoket, clientSoket.getPort());
        m_order.put(clientSoket, clientInfo);


        int count = TcpUtil.receiveInt(clientSoket);



        clientSynchronize(() -> setLastTimeCallback(clientInfo));


        int length = TcpUtil.receiveInt(clientSoket);
        Console.writeLine("Count : %d", length);

        clientSynchronize(() -> setCompletedCallback(clientInfo));

        send(clientSoket, count, length);




    }

    private void generatePasswords(Socket clientSocket ){
        Console.writeLine("Host : %s Port : %d Local port : %d %n", clientSocket.getInetAddress().getHostAddress(),
                clientSocket.getPort(), clientSocket.getLocalPort());
        subscribeRunnable(() -> generatePasswordsCallback(clientSocket), clientSocket,
                ex -> Console.writeLine("generatePaswords %s", ex.getMessage()), () -> m_order.remove(clientSocket));

    }

    private void accepOrder() throws IOException {
        Console.writeLine("Random Pasword Generator is waiting for Client");
        handleClient(m_serverSoket.accept());
    }

    private void runForAccept () {
        for (;;) {
            subscribeRunnable(this::accepOrder, ex -> Console.Error.writeLine(ex.getMessage()));
        }
    }

    private void runServerCallback()
    {

        runForAccept();

    }

    private boolean isRemovable(Socket socket, int threshold, String schedulerThresholdUnitStr)
    {
        var now = LocalDateTime.now();
        var ci = m_order.get(socket);

        var status = ChronoUnit.valueOf(schedulerThresholdUnitStr).between(ci.getLastTime(), now) > threshold;

        subscribeRunnable(() -> {if (status) socket.close();}, ex -> Console.Error.writeLine(ex.getMessage()));

        return status;
    }

    private void schedulerSynchronizedCallback() {
        m_order.keySet().removeIf(s -> isRemovable(s, m_schedulerThreshold, m_schedulerThresholdUnitStr));
    }

    private void schedulerCallback() {
        Console.writeLine("Client Size:%d", m_order.size());

    }

    public OrderServiceServerRunner(ServerSocket serverSoket, ExecutorService treadPool, Map<Socket, OrderInfo> orders)
    {
        this.m_serverSoket = serverSoket;
        this.m_treadPool = treadPool;
        this.m_order = orders;
    }


        @Override
        public void run(ApplicationArguments args) throws Exception {
            new Scheduler(m_schedulerInterval, TimeUnit.valueOf(m_schedulerIntervalUnitStr)).schedule(this::schedulerCallback);
            m_treadPool.execute(this::runServerCallback);
        }
}
