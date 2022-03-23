package org.csystem.application.server.randompasswordgenerator.orderinfo;

import java.net.Socket;
import java.time.LocalDateTime;

public class OrderInfo {
    private Socket m_socket;
    private boolean m_completed;
    private LocalDateTime m_lastTime =LocalDateTime.now();
    private int m_ephemeralPort;

    public OrderInfo(Socket socket, boolean completed, LocalDateTime lastTime, int ephemeralPort) {
        m_socket = socket;
        m_completed = completed;
        m_lastTime = lastTime;
        m_ephemeralPort = ephemeralPort;
    }

    public OrderInfo(Socket m_socket, int m_ephemeralPort)
    {
        this.m_socket = m_socket;

        this.m_ephemeralPort = m_ephemeralPort;
    }

    public Socket getSocket()
    {
        return m_socket;
    }

    public void setSocket(Socket m_socket)
    {
        this.m_socket = m_socket;
    }

    public boolean isCompleted()
    {
        return m_completed;
    }

    public void setCompleted()
    {
        setCompleted(true);
    }

    public void setCompleted(boolean m_completed)
    {
        this.m_completed = m_completed;
    }

    public LocalDateTime getLastTime()
    {
        return m_lastTime;
    }

    public void setLastTime(LocalDateTime m_lastTime)
    {
        this.m_lastTime = m_lastTime;
    }

    public int getEphemeralPort()
    {
        return m_ephemeralPort;
    }

    public void setEphemeralPort(int m_ephemeralPort)
    {
        this.m_ephemeralPort = m_ephemeralPort;
    }
}
