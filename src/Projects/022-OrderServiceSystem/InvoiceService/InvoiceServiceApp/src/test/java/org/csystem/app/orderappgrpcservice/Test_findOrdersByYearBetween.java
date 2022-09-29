package org.csystem.app.orderappgrpcservice;

import org.csystem.app.grpc.OrderAppGRPCService;
import org.csystem.app.order.Order;
import org.csystem.app.order.OrderClassFactory;
import org.csystem.app.util.ServiceStreamUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

@SpringBootTest
public class Test_findOrdersByYearBetween {
    private final ApplicationContext m_applicationContext;
    //...

    public Test_findOrdersByYearBetween(ApplicationContext applicationContext) { m_applicationContext = applicationContext; }

    @Test
    public void test_findOrdersByYearBetween()
    {
        int beginYear = 0; //Derleyici bizim kodumuzu bilmek zorunda değildir.
        int endYear = 0;

        var orderList = OrderClassFactory.getAllOrders();
        var orderYearList = orderList.stream()
                .map(Order::getOrderDate)
                .map(LocalDate::getYear)
                .sorted()
                .collect(Collectors.toList());

        if(orderYearList.size() == 1){
            beginYear = orderYearList.get(0);
            endYear = orderYearList.get(0);
        }else if(orderYearList.size() >= 2){
            beginYear = orderYearList.get(0);
            endYear = orderYearList.get(1);
        }

        var orderAppGRPCService = m_applicationContext.getBean(OrderAppGRPCService.class);

        var orderInfoDTOList
                = ServiceStreamUtil.getIterableToList(orderAppGRPCService.findOrdersByYearBetween(beginYear, endYear));

        Assert.isTrue(orderInfoDTOList.size() > 0, "There is at least one record");
    }
}
