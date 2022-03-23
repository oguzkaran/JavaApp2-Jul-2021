package org.csystem.application.server.randompasswordgenerator.data.dal;


import org.csystem.application.server.randompasswordgenerator.data.entity.Order;
import org.csystem.application.server.randompasswordgenerator.data.entity.OrderProducts;
import org.csystem.application.server.randompasswordgenerator.data.repository.IOrderProductsServiceRepository;
import org.csystem.application.server.randompasswordgenerator.data.repository.IOrderServiceRepository;
import org.springframework.stereotype.Component;

import static org.csystem.util.data.DatabaseUtil.*;

@Component
public class OrderServiceHelper {
    private final IOrderServiceRepository m_iOrderServiceRepository;
    private final IOrderProductsServiceRepository m_iOrderProductsServiceRepository;

    public OrderServiceHelper(IOrderServiceRepository iOrderServiceRepository,
                              IOrderProductsServiceRepository iOrderProductsServiceRepository) {
        m_iOrderServiceRepository = iOrderServiceRepository;
        m_iOrderProductsServiceRepository = iOrderProductsServiceRepository;
    }



    public Order saveOrder(Order order)
    {
        return doWorkForRepository(() -> m_iOrderServiceRepository.save(order), "OrderServiceDAL.saveOrder");
    }

    public OrderProducts saveOrderProducts(OrderProducts orderProducts)
    {
        return doWorkForRepository(() -> m_iOrderProductsServiceRepository.save(orderProducts), "OrderProductsServiceDAL.saveOrderProducts");
    }
}
