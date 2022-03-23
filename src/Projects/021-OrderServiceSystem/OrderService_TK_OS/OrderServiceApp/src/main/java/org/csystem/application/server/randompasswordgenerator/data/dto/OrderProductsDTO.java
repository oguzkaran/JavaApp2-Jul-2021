package org.csystem.application.server.randompasswordgenerator.data.dto;

import org.csystem.application.server.randompasswordgenerator.data.entity.OrderProducts;

import java.time.LocalDateTime;

public class OrderProductsDTO {//POJO (Plain Old Java Object)

    private LocalDateTime m_dateTime;
    private int m_clientId;
    private OrderProducts m_orderProducts;

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

    public OrderProducts getOrderProducts() {
        return m_orderProducts;
    }

    public void setOrderProducts(OrderProducts orderProducts) {
        this.m_orderProducts = orderProducts;
    }
}
