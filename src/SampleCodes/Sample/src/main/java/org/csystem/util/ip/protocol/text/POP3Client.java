package org.csystem.util.ip.protocol.text;

import java.io.*;
import java.net.Socket;
import java.util.List;

public final class POP3Client extends LoginClient implements ILogin, ITransmission {
    private final String m_server;
    private final String m_username;
    private final String m_password;
    private final BufferedWriter m_bw;
    private final BufferedReader m_br;

    private boolean login(String username, String password) throws IOException
    {
        m_bw.write(String.format("USER %s\r\n", username));
        //...
        m_bw.write(String.format("PASS %s\r\n", password));

        //...

        return true;
    }

    public POP3Client(String server, String username, String password) throws IOException
    {
        m_server = server;
        m_username = username;
        m_password = password;
        socket = new Socket(m_server, 110);
        m_br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        m_bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Override
    public boolean login() throws IOException
    {
        return login(m_username, m_password);
    }

    @Override
    public boolean logout()
    {
        return false;
    }

    @Override
    public void send(String text) throws IOException
    {

    }

    @Override
    public List<String> receive() throws IOException
    {
        return null;
    }
}
