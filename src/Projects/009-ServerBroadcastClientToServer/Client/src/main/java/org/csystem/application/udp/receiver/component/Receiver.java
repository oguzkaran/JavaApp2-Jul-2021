package org.csystem.application.udp.receiver.component;

import org.csystem.application.udp.receiver.runner.RandomPasswordClient;
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
import java.util.concurrent.ExecutorService;

@Component
public class Receiver {
    private final DatagramSocket m_datagramSocket;
    private final ExecutorService m_executorService;
    private final RandomPasswordClient m_randomPasswordClient;

    @Value("${receiver.bufSize}")
    private int m_bufSize;

    private void randomPasswordClientCallback(DatagramPacket datagramPacket)
    {
        if (datagramPacket.getLength() != 12) {
            Console.Error.writeLine("Invalid data format");
            return;
        }

        var data = datagramPacket.getData();
        var host = datagramPacket.getAddress().getHostAddress();
        var ephemeralPort = datagramPacket.getPort();

        var dataInfo = BitConverter.toIntArray(data, 3);
        var port = dataInfo[0];
        var count = dataInfo[1];
        var length = dataInfo[2];

        Console.writeLine("port:%d, count:%d length: %d received from %s:%d", port, count, length, host, ephemeralPort);

        m_randomPasswordClient
                .setHost(host)
                .setPort(port)
                .setCount(count)
                .setLength(length)
                .run();
    }

    public Receiver(DatagramSocket datagramSocket, ExecutorService executorService, RandomPasswordClient randomPasswordClient)
    {
        m_datagramSocket = datagramSocket;
        m_executorService = executorService;
        m_randomPasswordClient = randomPasswordClient;
    }

    public void run()
    {
        try {
            for (;;) {
                var datagramPacket = UdpUtil.receiveDatagramPacket(m_datagramSocket, m_bufSize);

                m_executorService.execute(() -> randomPasswordClientCallback(datagramPacket));
            }
        }
        catch (NetworkException ex) {
            Console.Error.writeLine("NetworkException:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Throwable:%s", ex.getMessage());
        }
    }
}
