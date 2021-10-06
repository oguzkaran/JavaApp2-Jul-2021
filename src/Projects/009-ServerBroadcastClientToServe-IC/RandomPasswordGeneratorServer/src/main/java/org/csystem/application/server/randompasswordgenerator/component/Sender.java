package org.csystem.application.server.randompasswordgenerator.component;

import org.csystem.util.console.Console;
import org.csystem.util.converter.BitConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

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

    private void broadcastCallback() throws IOException
    {
        var datagramPacket = new DatagramPacket(BitConverter.getBytes(m_port), 4, InetAddress.getByName(m_broadcastHost), m_broadcastPort);

        m_datagramSocket.send(datagramPacket);
    }

    public Sender(DatagramSocket datagramSocket)
    {
        m_datagramSocket = datagramSocket;
    }

    public void run()
    {
        subscribeRunnable(this::broadcastCallback, m_datagramSocket, ex -> Console.Error.writeLine(ex.getMessage()));
    }
}
