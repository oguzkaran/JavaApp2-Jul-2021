package org.csystem.application.udp.receiver.runner;

import org.csystem.util.console.Console;
import org.csystem.util.net.TcpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.Socket;
import java.util.Random;

@Component
public class RandomPasswordClient {
    private String m_host;
    private int m_port;
    private int m_count;
    private int m_length;

    private final Random m_random;

    private void clientCallback()
    {
        try (var socket = new Socket(m_host, m_port)) {
            var count = m_random.nextInt(m_count);
            var length = m_random.nextInt(m_length);

            TcpUtil.sendInt(socket, count);
            TcpUtil.sendInt(socket, length);

            if (TcpUtil.receiveInt(socket) == 1)
                for (var i = 0L; i < count; ++i) {
                    var password = TcpUtil.receiveString(socket, length);
                    Console.writeLine("%s", password);
                }
            else
                Console.writeLine("Invalid parameters for server!...");
        }
        catch (Throwable ex) {
            Console.Error.writeLine(ex.getMessage());
        }
    }

    private void runClient()
    {
        clientCallback();
    }

    public RandomPasswordClient(Random random)
    {
        m_random = random;
    }

    public RandomPasswordClient setHost(String host)
    {
        m_host = host;

        return this;
    }

    public RandomPasswordClient setPort(int port)
    {
        m_port = port;

        return this;
    }

    public RandomPasswordClient setCount(int count)
    {
        m_count = count;

        return this;
    }

    public RandomPasswordClient setLength(int length)
    {
        m_length = length;

        return this;
    }

    public void run()
    {
        this.runClient();
    }
}
