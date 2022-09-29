package org.csystem.app.orderappgrpcservice;

import org.csystem.app.grpc.OrderAppGRPCService;
import org.csystem.app.order.OrderClassFactory;
import org.csystem.app.util.ServiceStreamUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@SpringBootTest
public class Test_findOrdersByMonthAndYear {
    private final ApplicationContext m_applicationContext;
    private final OrderAppGRPCService m_orderAppGRPCService;
    //...

    public Test_findOrdersByMonthAndYear(ApplicationContext applicationContext)
    {
        m_applicationContext = applicationContext;
        m_orderAppGRPCService = m_applicationContext.getBean(OrderAppGRPCService.class);
    }

    @Test
    public void test_findOrdersByMonthAndYear()
    {
        var random = new Random();

        var order = OrderClassFactory.getRandomOrder(random);

        var orderInfoDTOList
                = ServiceStreamUtil.getIterableToList(m_orderAppGRPCService.findOrdersByMonthAndYear(order.getOrderDate().getMonthValue(), order.getOrderDate().getYear()));

        Assert.isTrue(orderInfoDTOList.size() > 0, "There is at least one record");
    }
}
