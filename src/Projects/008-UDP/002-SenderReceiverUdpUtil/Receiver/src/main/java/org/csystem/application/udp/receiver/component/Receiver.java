package org.csystem.application.udp.receiver.component;

import org.csystem.util.console.Console;
import org.csystem.util.converter.BitConverter;
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
                var datagramPacket = UdpUtil.receiveDatagramPacket(m_port, m_bufSize);
                var length = datagramPacket.getLength();
                var host = datagramPacket.getAddress().getHostAddress();
                var port = datagramPacket.getPort();
                var text = BitConverter.toString(datagramPacket.getData(), 0, length);

                Console.writeLine("%d bytes data (%s) received from %s:%d", length, text, host, port);
            }
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Throwable:%s", ex.getMessage());
        }
    }
}
