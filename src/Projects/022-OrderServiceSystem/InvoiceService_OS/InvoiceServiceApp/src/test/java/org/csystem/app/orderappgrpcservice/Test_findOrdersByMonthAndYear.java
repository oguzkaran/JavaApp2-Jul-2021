package org.csystem.app.orderappgrpcservice;

import org.csystem.app.grpc.OrderAppGRPCService;
import org.csystem.app.util.ServiceStreamUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@SpringBootTest
public class Test_findOrdersByMonthAndYear {
    private final ApplicationContext m_applicationContext;
    //...

    public Test_findOrdersByMonthAndYear(ApplicationContext applicationContext) { m_applicationContext = applicationContext; }

    @Test
    public void test_findOrdersByMonthAndYear()
    {
        var month = 12;
        var year = 2021;
        var orderAppGRPCService = m_applicationContext.getBean(OrderAppGRPCService.class);

        var orderInfoDTOList
                = ServiceStreamUtil.getIterableToList(orderAppGRPCService.findOrdersByMonthAndYear(month, year));

        Assert.isTrue(orderInfoDTOList.size() > 0, "There is at least one record");
    }
}
