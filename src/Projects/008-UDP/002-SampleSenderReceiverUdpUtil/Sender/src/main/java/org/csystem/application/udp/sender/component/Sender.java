package org.csystem.application.udp.sender.component;

import org.csystem.util.console.Console;
import org.csystem.util.net.UdpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.DatagramSocket;

@Component
public class Sender {
    @Value("${sender.host}")
    private String m_host;

    @Value("${sender.port}")
    private int m_port;

    public void run()
    {
        try (var datagramSocket = new DatagramSocket()) {
            for (;;) {
                var text = Console.read("Text:");

                if (text.equals("exit"))
                    break;

                UdpUtil.sendString(m_host, m_port, text);
            }
        }
        catch (IOException ex) {
            Console.Error.writeLine("IOException:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Throwable:%s", ex.getMessage());
        }
    }
}
