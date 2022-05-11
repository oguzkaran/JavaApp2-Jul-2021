package org.csystem.app.service.rest.greeting.dto;

public class GreetingResponseDTO {
    private String m_message;
    private String m_maritalStatus;

    public String getMessage()
    {
        return m_message;
    }

    public void setMessage(String message)
    {
        m_message = message;
    }

    public String getMaritalStatus()
    {
        return m_maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus)
    {
        m_maritalStatus = maritalStatus;
    }
}
