package com.tevfikkoseli.app.service.data.dto;

import com.tevfikkoseli.app.service.data.entity.OrderProduct;

import java.time.LocalDateTime;
import java.util.Set;

public class OrderDTO {//POJO (Plain Old Java Object)
    private LocalDateTime m_dateTime;
    private int m_clientId;
    private Set<OrderProduct> m_orderProducts;
    public LocalDateTime getDateTime() {
        return m_dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        m_dateTime = dateTime;
    }

    public int getClientId() {
        return m_clientId;
    }

    public void setClientId(int clientId) {
        m_clientId = clientId;
    }

    public Set<OrderProduct> getOrderProducts() {
        return m_orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        m_orderProducts = orderProducts;
    }
}
