package com.tevfikkoseli.app.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tevfikkoseli.app.service.data.entity.OrderProduct;

import java.time.LocalDateTime;
import java.util.Set;

public class OrderDTO {
    private LocalDateTime m_dateTime;
    private int m_clientId;
    private Set<OrderProduct> m_orderProducts;
    public LocalDateTime getDateTime() {
        return m_dateTime;
    }

    @JsonProperty("orderDateTime")
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
