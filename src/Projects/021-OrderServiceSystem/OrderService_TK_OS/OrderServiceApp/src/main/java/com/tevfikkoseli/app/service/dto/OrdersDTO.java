package com.tevfikkoseli.app.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tevfikkoseli.app.service.data.entity.OrderProduct;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class OrdersDTO {
    private List<OrderDTO> m_orderDTOs;

    public List<OrderDTO> getOrderDTOs()
    {
        return m_orderDTOs;
    }

    @JsonProperty("orders")
    public void setOrderDTOs(List<OrderDTO> orderDTOs)
    {
        m_orderDTOs = orderDTOs;
    }
}
