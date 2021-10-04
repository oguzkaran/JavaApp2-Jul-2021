package org.csystem.application.udp.receiver.component;

import org.csystem.util.console.Console;
import org.csystem.util.converter.BitConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

@Component
public class Receiver {
    @Value("${receiver.port}")
    private int m_port;

    @Value("${receiver.bufSize}")
    private int m_bufSize;

    public void run()
    {
        try (var datagramSocket = new DatagramSocket(m_port)) {
            var buf = new byte[m_bufSize];

            for (;;) {
                Console.writeLine("Waiting for a sender");
                var datagramPacket = new DatagramPacket(buf, buf.length);

                datagramSocket.receive(datagramPacket);
                var length = datagramPacket.getLength();
                byte [] data = Arrays.copyOf(datagramPacket.getData(), length);
                var text = BitConverter.toString(data);
                var host = datagramPacket.getAddress().getHostAddress();
                var port = datagramPacket.getPort();

                Console.writeLine("%d bytes data (%s) received from %s:%d", length, text, host, port);
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
