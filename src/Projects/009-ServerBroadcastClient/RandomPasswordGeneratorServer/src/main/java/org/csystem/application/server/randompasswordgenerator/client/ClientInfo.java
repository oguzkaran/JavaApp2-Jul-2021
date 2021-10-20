package org.csystem.application.server.randompasswordgenerator.client;

import java.net.Socket;
import java.time.LocalDateTime;

public class ClientInfo {
    private Socket m_socket;
    private boolean m_completed;
    private LocalDateTime m_lastTime = LocalDateTime.now();
    private int m_ephemeralPort;

    //...

    public ClientInfo(Socket socket, int ephemeralPort)
    {
        m_socket = socket;
        m_ephemeralPort = ephemeralPort;
    }

    public Socket getSocket()
    {
        return m_socket;
    }

    public boolean isCompleted()
    {
        return m_completed;
    }

    public void setCompleted()
    {
        setCompleted(true);
    }

    public void setCompleted(boolean completed)
    {
        m_completed = completed;
    }

    public void setSocket(Socket socket)
    {
        m_socket = socket;
    }

    public LocalDateTime getLastTime()
    {
        return m_lastTime;
    }

    public void setLastTime(LocalDateTime lastTime)
    {
        m_lastTime = lastTime;
    }

    public int getEphemeralPort()
    {
        return m_ephemeralPort;
    }

    public void setEphemeralPort(int ephemeralPort)
    {
        m_ephemeralPort = ephemeralPort;
    }
}
