package com.tevfikkoseli.app.service.dto;

import com.tevfikkoseli.app.service.data.entity.OrderProduct;

import java.time.LocalDateTime;

public class OrderProductsDTO {

    private LocalDateTime m_dateTime;
    private int m_clientId;
    private OrderProduct m_orderProducts;

    public LocalDateTime getDateTime() {
        return m_dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.m_dateTime = dateTime;
    }

    public int getClientId() {
        return m_clientId;
    }

    public void setClientId(int clientId) {
        this.m_clientId = clientId;
    }

    public OrderProduct getOrderProducts() {
        return m_orderProducts;
    }

    public void setOrderProducts(OrderProduct orderProducts) {
        this.m_orderProducts = orderProducts;
    }
}
