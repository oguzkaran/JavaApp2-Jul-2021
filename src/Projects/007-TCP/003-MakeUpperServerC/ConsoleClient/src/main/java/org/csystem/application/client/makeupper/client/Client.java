package org.csystem.application.client.makeupper.client;

import org.csystem.util.console.Console;
import org.csystem.util.net.TcpUtil;
import org.springframework.stereotype.Component;

import java.net.Socket;
import java.nio.charset.StandardCharsets;

@Component
public class Client {
    private final Socket m_socket;

    public Client(Socket socket)
    {
        m_socket = socket;
    }

    public void run()
    {
        try (m_socket) {
            for (;;) {
                var text = Console.read("Text:");

                if (text.equals("quit"))
                    break;

                TcpUtil.sendStringViaLength(m_socket, text, StandardCharsets.US_ASCII);
                var result = TcpUtil.receiveStringViaLength(m_socket);

                Console.writeLine(result);
            }
        }
        catch (Throwable ex) {
            Console.Error.writeLine(ex.getMessage());
        }
    }
}
