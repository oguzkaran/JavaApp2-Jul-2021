package org.csystem.application.server.randompasswordgenerator.service;

import org.csystem.application.server.randompasswordgenerator.data.dal.OrderServiceHelper;
import org.csystem.application.server.randompasswordgenerator.data.dto.OrderDTO;
import org.csystem.application.server.randompasswordgenerator.mapper.IOrderMapper;
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
