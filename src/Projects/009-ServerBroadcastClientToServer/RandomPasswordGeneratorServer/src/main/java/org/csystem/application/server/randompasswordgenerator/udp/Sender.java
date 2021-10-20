package org.csystem.application.server.randompasswordgenerator.udp;

import org.csystem.util.console.Console;
import org.csystem.util.converter.BitConverter;
import org.csystem.util.net.UdpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

import static org.csystem.util.exception.ExceptionUtil.subscribeRunnable;

@Component
public class Sender {
    private final DatagramSocket m_datagramSocket;

    @Value("${socket.port}")
    private int m_port;

    @Value("${datagram.broadcast.port}")
    private int m_broadcastPort;

    @Value("${datagram.broadcast.host}")
    private String m_broadcastHost;

    @Value("${password.maxcount}")
    private int m_count;

    @Value("${password.maxlength}")
    private int m_length;

    private void broadcastCallback() throws IOException
    {
        var data = BitConverter.getBytes(m_port, m_count, m_length);

        var datagramPacket = new DatagramPacket(data, data.length, InetAddress.getByName(m_broadcastHost), m_broadcastPort);

        m_datagramSocket.send(datagramPacket);
    }

    public Sender(DatagramSocket datagramSocket)
    {
        m_datagramSocket = datagramSocket;
    }

    @Scheduled(fixedRate = 3000)
    public void run()
    {
        subscribeRunnable(this::broadcastCallback, ex -> Console.Error.writeLine(ex.getMessage()));
    }
}
