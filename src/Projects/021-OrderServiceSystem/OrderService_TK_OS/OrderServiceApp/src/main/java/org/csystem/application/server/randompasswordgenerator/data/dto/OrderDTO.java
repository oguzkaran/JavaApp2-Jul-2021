package org.csystem.application.server.randompasswordgenerator.data.dto;

import org.csystem.application.server.randompasswordgenerator.data.entity.OrderProducts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class OrderDTO {//POJO (Plain Old Java Object)

    private LocalDateTime m_dateTime;
    private int m_clientId;
    private Set<OrderProducts> m_orderProducts;

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

    public Set<OrderProducts> getOrderProducts() {
        return m_orderProducts;
    }

    public void setOrderProducts(Set<OrderProducts> orderProducts) {
        m_orderProducts = orderProducts;
    }
}
