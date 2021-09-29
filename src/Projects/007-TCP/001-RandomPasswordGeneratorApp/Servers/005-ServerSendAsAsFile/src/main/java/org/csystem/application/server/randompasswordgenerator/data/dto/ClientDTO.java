package org.csystem.application.server.randompasswordgenerator.data.dto;

public class ClientDTO {
    private String m_host;
    private int m_count;
    private int m_length;
    private boolean m_status;
    private String m_passwords;
    private String m_filename;


    public ClientDTO()
    {}

    public ClientDTO(String host, int count, int length, boolean status, String passwords, String filename)
    {
        m_host = host;
        m_count = count;
        m_length = length;
        m_status = status;
        m_passwords = passwords;
        m_filename = filename;
    }

    public String getHost()
    {
        return m_host;
    }

    public void setHost(String host)
    {
        m_host = host;
    }

    public int getCount()
    {
        return m_count;
    }

    public void setCount(int count)
    {
        m_count = count;
    }

    public int getLength()
    {
        return m_length;
    }

    public void setLength(int length)
    {
        m_length = length;
    }

    public boolean isStatus()
    {
        return m_status;
    }

    public void setStatus(boolean status)
    {
        m_status = status;
    }

    public String getPasswords()
    {
        return m_passwords;
    }

    public void setPasswords(String passwords)
    {
        m_passwords = passwords;
    }

    public String getFilename()
    {
        return m_filename;
    }

    public void setFilename(String filename)
    {
        m_filename = filename;
    }
}
