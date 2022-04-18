package com.tevfikkoseli.app.service.data.dal;


import com.tevfikkoseli.app.service.data.repository.IOrderRepository;
import com.tevfikkoseli.app.service.data.entity.Order;
import com.tevfikkoseli.app.service.data.entity.OrderProducts;
import com.tevfikkoseli.app.service.data.repository.IOrderProductsRepository;
import org.springframework.stereotype.Component;

import static org.csystem.util.data.DatabaseUtil.*;

@Component
public class OrderServiceHelper {
    private final IOrderRepository m_iOrderServiceRepository;
    //private final IOrderProductsRepository m_iOrderProductsServiceRepository;


    public OrderServiceHelper(IOrderRepository iOrderServiceRepository)
    {
        m_iOrderServiceRepository = iOrderServiceRepository;
    }

    public Order saveOrder(Order order)
    {
        return doWorkForRepository(() -> m_iOrderServiceRepository.save(order), "OrderServiceDAL.saveOrder");
    }

    /*
    public OrderProducts saveOrderProducts(OrderProducts orderProducts)
    {
        return doWorkForRepository(() -> m_iOrderProductsServiceRepository.save(orderProducts), "OrderProductsServiceDAL.saveOrderProducts");
    }
    */

}
