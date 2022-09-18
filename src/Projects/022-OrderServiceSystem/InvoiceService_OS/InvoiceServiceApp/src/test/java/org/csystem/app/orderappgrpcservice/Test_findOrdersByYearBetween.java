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
public class Test_findOrdersByYearBetween {
    private final ApplicationContext m_applicationContext;
    //...

    public Test_findOrdersByYearBetween(ApplicationContext applicationContext) { m_applicationContext = applicationContext; }

    @Test
    public void test_findOrdersByYearBetween()
    {
        var beginYear = 2021;
        var endYear = 2022;
        var orderAppGRPCService = m_applicationContext.getBean(OrderAppGRPCService.class);

        var orderInfoDTOList
                = ServiceStreamUtil.getIterableToList(orderAppGRPCService.findOrdersByYearBetween(beginYear, endYear));

        Assert.isTrue(orderInfoDTOList.size() > 0, "There is at least one record");
    }
}
