package org.csystem.app.orderappgrpcservice;

import org.csystem.app.api.dto.OrderInfoDTO;
import org.csystem.app.api.dto.OrderProductInfoDTO;
import org.csystem.app.grpc.OrderAppGRPCService;
import org.csystem.app.util.ServiceStreamUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import java.util.List;
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
        int clientId = 141;

        //var type. var type just using as local variable type
        var orderAppGRPCService = m_applicationContext.getBean(OrderAppGRPCService.class);
        var orderInfoDTOList
                = ServiceStreamUtil.getIterableToList(orderAppGRPCService.findOrdersClientId(clientId));

        Assert.isTrue(orderInfoDTOList.size() > 0, "There is at least one record");
    }
}
