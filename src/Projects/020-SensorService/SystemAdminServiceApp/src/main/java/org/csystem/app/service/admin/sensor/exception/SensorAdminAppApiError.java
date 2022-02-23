package org.csystem.app.service.admin.sensor.exception;

import org.csystem.util.exception.api.ApiError;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class SensorAdminAppApiError extends ApiError {
    private LocalDateTime m_errorTime = LocalDateTime.now();
    private Object m_rejectedValue;
    private String m_debugMessage;


    public SensorAdminAppApiError(HttpStatus status, String message,Object rejectedValue, String debugMessage)
    {
        super(status, message);
        m_rejectedValue = rejectedValue;
        m_debugMessage = debugMessage;
    }

    public LocalDateTime getErrorTime()
    {
        return m_errorTime;
    }

    public void setErrorTime(LocalDateTime errorTime)
    {
        m_errorTime = errorTime;
    }

    public Object getRejectedValue()
    {
        return m_rejectedValue;
    }

    public void setRejectedValue(Object rejectedValue)
    {
        m_rejectedValue = rejectedValue;
    }

    public String getDebugMessage()
    {
        return m_debugMessage;
    }

    public void setDebugMessage(String debugMessage)
    {
        m_debugMessage = debugMessage;
    }
}
