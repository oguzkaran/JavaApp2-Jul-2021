package org.csystem.app.orderappgrpcservice;

import org.csystem.app.api.dto.OrderProductInfoDTO;
import org.csystem.app.grpc.OrderAppGRPCService;
import org.csystem.app.order.OrderClassFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@SpringBootTest
public class Test_findOrderProductsByOrderId {
    private final ApplicationContext m_applicationContext;
    //...

    public Test_findOrderProductsByOrderId(ApplicationContext applicationContext) { m_applicationContext = applicationContext; }

    @Test
    public void test_findOrderProductsByOrderId()
    {
        var random = new Random();

        //List<T> interface ini destekleyen veri yapılarının nesnelerinin adresleri. Liste tarzı veri yapısı
        List<Long> orderIdList = new ArrayList<>(){{
           //non-static initializer
           this.add(OrderClassFactory.getRandomOrder(random).getOrderId());
           this.add(OrderClassFactory.getRandomOrder(random).getOrderId());
        }};

        var orderAppGRPCService = m_applicationContext.getBean(OrderAppGRPCService.class);
        var orderProductInfoDTOList = orderIdList.stream()
                .flatMap(orderId -> StreamSupport.stream(orderAppGRPCService.findOrderProductsByOrderId(orderId).spliterator(), false))
                .collect(Collectors.toList());

        Assert.isTrue(orderProductInfoDTOList.size() > 0, "There is at least one record");
    }
}
