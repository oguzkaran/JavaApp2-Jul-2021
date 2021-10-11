package org.csystem.application.udp.receiver.component;

import org.csystem.util.console.Console;
import org.csystem.util.converter.BitConverter;
import org.csystem.util.net.UdpUtil;
import org.csystem.util.net.exception.NetworkException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

@Component
public class Receiver {
    private final DatagramSocket m_datagramSocket;

    @Value("${receiver.bufSize}")
    private int m_bufSize;

    public Receiver(DatagramSocket datagramSocket)
    {
        m_datagramSocket = datagramSocket;
    }

    public void run()
    {
        try  {
            var buf = new byte[m_bufSize];

            for (;;)
                Console.writeLine(UdpUtil.receiveString(m_datagramSocket, m_bufSize));
        }
        catch (NetworkException ex) {
            Console.Error.writeLine("NetworkException:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Throwable:%s", ex.getMessage());
        }
    }
}
