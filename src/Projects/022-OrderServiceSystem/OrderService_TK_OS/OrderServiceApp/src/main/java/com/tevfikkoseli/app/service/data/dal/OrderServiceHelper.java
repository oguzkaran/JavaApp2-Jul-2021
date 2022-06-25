package com.tevfikkoseli.app.service.data.dal;


import com.tevfikkoseli.app.service.data.entity.OrderProduct;
import com.tevfikkoseli.app.service.data.repository.IOrderProductRepository;
import com.tevfikkoseli.app.service.data.repository.IOrderRepository;
import com.tevfikkoseli.app.service.data.entity.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class OrderServiceHelper {
    private final IOrderRepository m_orderRepository;
    private final IOrderProductRepository m_orderProductRepository;

    public OrderServiceHelper(IOrderRepository orderRepository, IOrderProductRepository orderProductRepository)
    {
        m_orderRepository = orderRepository;
        m_orderProductRepository = orderProductRepository;
    }


    public Iterable<Order> findOrdersByMonthAndYear(int month, int year)
    {
        return m_orderRepository.findByMonthAndYear(month, year);
    }

    public Iterable<Order> findOrdersByYearBetween(int begin, int end)
    {
        return m_orderRepository.findByYearBetween(begin, end);
    }

    public Iterable<Order> findOrdersByDate(LocalDate date)
    {
        return m_orderRepository.findByDate(date);
    }

    public Iterable<Order> findOrdersByClientId(int clientId)
    {
        return m_orderRepository.findByClientId(clientId);
    }

    public Iterable<Order> findOrdersByDateTimeBetween(LocalDateTime begin, LocalDateTime end)
    {
        return m_orderRepository.findByDateTimeBetween(begin, end);
    }

    public Iterable<Order> findOrdersByProductId(int productId)
    {
        return m_orderRepository.findByProductId(productId);
    }

    public Iterable<OrderProduct> findOrderProductsByOrderId(long orderId)
    {
        return m_orderProductRepository.findByOrderId(orderId);
    }

    public Iterable<OrderProduct> findOrderProductsByProductId(int productId)
    {
        return m_orderProductRepository.findByProductId(productId);
    }

    public Order saveOrder(Order order)
    {
        return m_orderRepository.save(order);
    }
}
