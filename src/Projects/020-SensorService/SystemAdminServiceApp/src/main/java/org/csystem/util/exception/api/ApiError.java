package org.csystem.util.exception.api;

import org.springframework.http.HttpStatus;

public class ApiError {
    private HttpStatus m_status;
    private String m_message;

    public ApiError()
    {
    }

    public ApiError(HttpStatus status, String message)
    {
        m_status = status;
        m_message = message;
    }

    public HttpStatus getStatus()
    {
        return m_status;
    }

    public void setStatus(HttpStatus status)
    {
        m_status = status;
    }

    public String getMessage()
    {
        return m_message;
    }

    public void setMessage(String message)
    {
        m_message = message;
    }
}
