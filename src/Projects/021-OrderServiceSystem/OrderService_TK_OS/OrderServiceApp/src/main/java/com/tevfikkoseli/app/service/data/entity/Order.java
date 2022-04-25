package com.tevfikkoseli.app.service.data.entity;

import java.time.LocalDateTime;

public class Order { //POJO (Plain Old Java Object)
    private Long m_id;
    private LocalDateTime m_oDateTime = LocalDateTime.now();
    private int m_clientId;

    public Order(long id, LocalDateTime oDateTime, int clientId)
    {
        m_id = id;
        m_oDateTime = oDateTime;
        m_clientId = clientId;
    }

    public Long getId()
    {
        return m_id;
    }

    public void setId(Long id)
    {
        m_id = id;
    }

    public LocalDateTime getoDateTime()
    {
        return m_oDateTime;
    }

    public void setoDateTime(LocalDateTime oDateTime)
    {
        this.m_oDateTime = oDateTime;
    }

    public int getClientId()
    {
        return m_clientId;
    }

    public void setClientId(int clientId)
    {
        this.m_clientId = clientId;
    }
}
