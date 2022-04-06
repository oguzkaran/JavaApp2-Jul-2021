package com.tevfikkoseli.app.service.service;

import com.tevfikkoseli.app.service.mapper.IOrderMapper;
import com.tevfikkoseli.app.service.data.dal.OrderServiceHelper;
import com.tevfikkoseli.app.service.data.dto.OrderDTO;
import org.springframework.stereotype.Service;
import static org.csystem.util.data.DatabaseUtil.*;

@Service
public class RandomPasswordService {
    private final OrderServiceHelper m_orderServiceHelper;
    private final IOrderMapper m_orderMapper;

    private OrderDTO saveOrderCallback(OrderDTO orderDTO)
    {

        var order = m_orderServiceHelper.saveOrder(m_orderMapper.toOrder(orderDTO));

        return m_orderMapper.toOrderDTO(order);

    }

    public RandomPasswordService(OrderServiceHelper randomPasswordHelper, IOrderMapper orderMapper)
    {
        m_orderServiceHelper = randomPasswordHelper;
        m_orderMapper = orderMapper;
    }

    public OrderDTO saveOrder(OrderDTO orderDTO)
    {
        return doWorkForRepository(() -> saveOrderCallback(orderDTO),  "RandomPasswordService.saveClient");

    }
}
