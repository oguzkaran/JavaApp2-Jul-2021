package org.csystem.application.udp.receiver.component;

import org.csystem.util.console.Console;
import org.csystem.util.net.UdpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    @Value("${receiver.port}")
    private int m_port;

    @Value("${receiver.bufSize}")
    private int m_bufSize;

    public void run()
    {
        try {
            for (;;) {
                Console.writeLine("Waiting for a sender");
                var text = UdpUtil.receiveString(m_port, m_bufSize);

                Console.writeLine("Text:%s", text);
            }
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Exception:%s", ex.getMessage());
        }
    }
}
