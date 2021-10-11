package org.csystem.application.udp.receiver.runner;

import org.csystem.util.console.Console;
import org.csystem.util.net.TcpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.Socket;

@Component
public class RandomPasswordClient {
    private String m_host;
    private int m_port;

    private void clientCallback()
    {
        try (var socket = new Socket(m_host, m_port)) {
            var count = Console.readInt("Count?");

            if (count <= 0)
                return;

            var length = Console.readInt("Number of Characters?");

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

    public void setHost(String host)
    {
        m_host = host;
    }

    public void setPort(int port)
    {
        m_port = port;
    }

    public void run()
    {
        this.runClient();
    }
}
