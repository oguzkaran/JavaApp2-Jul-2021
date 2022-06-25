package com.tevfikkoseli.app.service.data.entity;

import java.time.LocalDateTime;
import java.util.Optional;

public class Order { //POJO (Plain Old Java Object)
    private long m_id;
    private Optional<LocalDateTime> m_oDateTime;
    private int m_clientId;

    public Order()
    {}

    public Order(long id, LocalDateTime oDateTime, int clientId)
    {
        m_id = id;
        m_oDateTime = Optional.ofNullable(oDateTime);
        m_clientId = clientId;
    }

    public Order(long id, int clientId)
    {
        this(id, null, clientId);
    }

    public long getId()
    {
        return m_id;
    }

    public void setId(long id)
    {
        m_id = id;
    }

    public Optional<LocalDateTime> getoDateTime()
    {
        return m_oDateTime;
    }

    public void setoDateTime(Optional<LocalDateTime> oDateTime)
    {
        m_oDateTime = oDateTime;
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
