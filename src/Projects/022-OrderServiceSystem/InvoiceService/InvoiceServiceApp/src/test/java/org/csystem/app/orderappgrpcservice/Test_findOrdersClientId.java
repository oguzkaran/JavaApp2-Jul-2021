package org.csystem.app.orderappgrpcservice;

import org.csystem.app.api.dto.OrderInfoDTO;
import org.csystem.app.api.dto.OrderProductInfoDTO;
import org.csystem.app.grpc.OrderAppGRPCService;
import org.csystem.app.order.OrderClassFactory;
import org.csystem.app.util.ServiceStreamUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@SpringBootTest
public class Test_findOrdersClientId {
    private final ApplicationContext m_applicationContext;
    //...

    //No Autowired annotation. With Spring 4.3
    public Test_findOrdersClientId(ApplicationContext applicationContext) { m_applicationContext = applicationContext; }

    @Test
    public void test_findOrdersClientId()
    {
        var random = new Random();
        //int clientId = 141;

        var order = OrderClassFactory.getRandomOrder(random);

        //var type. var type just using as local variable type
        var orderAppGRPCService = m_applicationContext.getBean(OrderAppGRPCService.class);
        var orderInfoDTOList
                = ServiceStreamUtil.getIterableToList(orderAppGRPCService.findOrdersClientId(order.getClientId()));

        Assert.isTrue(orderInfoDTOList.size() > 0, "There is at least one record");
    }
}
