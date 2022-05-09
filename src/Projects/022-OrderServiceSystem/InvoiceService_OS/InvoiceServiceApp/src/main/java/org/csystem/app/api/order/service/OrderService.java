package org.csystem.app.api.order.service;

import org.csystem.app.api.order.domain.Order;
import org.csystem.app.api.order.repository.IOrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.csystem.util.data.DatabaseUtil.doWorkForRepository;
import static org.csystem.util.data.DatabaseUtil.doWorkForService;

/**
 * @author onder sahin
 */
@Service
public class OrderService {

    private IOrderRepository orderRepository;

    public OrderService(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    private Order saveInvoiceCallback(Order order)
    {
        return orderRepository.save(order);
    }

    private Order updateInvoiceCallback(Order order)
    {
        return orderRepository.save(order);
    }

    private Order deleteInvoiceCallback(Order order)
    {
        orderRepository.deleteById(order.getId());
        return order;
    }

    public Order saveOrder(Order order)
    {
        return doWorkForService(() -> saveInvoiceCallback(order), "OrderService.saveInvoice");
    }

    public Order updateOrder(Order order)
    {
        return doWorkForService(() -> updateInvoiceCallback(order), "OrderService.updateInvoice");
    }


    public Order deleteOrder(Order order)
    {
        return doWorkForService(() -> deleteInvoiceCallback(order), "OrderService.deleteInvoice");
    }

    public Optional<Order> findOrderById(int id)
    {
        return doWorkForRepository(() -> orderRepository.findById(id), "OrderService.findOrderById");
    }
}
