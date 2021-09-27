package org.csystem.application.client.console.general.runner;

import org.csystem.util.console.Console;
import org.csystem.util.net.TcpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.Socket;

@Component
public class RandomPasswordClientStayConnected {
    @Value("${randomServer.host}")
    private String m_host;

    @Value("${randomServerStayConnected.port}")
    private int m_port;

    private void clientCallback()
    {
        try (var socket = new Socket(m_host, m_port)) {
            for (;;) {
                var count = Console.readInt("Count?");

                TcpUtil.sendInt(socket, count);

                if (count <= 0)
                    break;

                var length = Console.readInt("Number of Characters?");

                TcpUtil.sendInt(socket, length);

                if (TcpUtil.receiveInt(socket) == 1)
                    for (var i = 0L; i < count; ++i) {
                        var password = TcpUtil.receiveString(socket, length);
                        Console.writeLine("%s", password);
                    }
                else
                    Console.writeLine("Invalid parameters for server!...");
            }
        }
        catch (Throwable ex) {
            Console.Error.writeLine(ex.getMessage());
        }
    }

    private void runClient()
    {
        clientCallback();
    }

    public void run() throws Exception
    {
        this.runClient();
    }
}
