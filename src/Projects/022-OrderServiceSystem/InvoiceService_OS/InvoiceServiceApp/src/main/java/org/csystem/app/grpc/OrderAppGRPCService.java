package org.csystem.app.grpc;

import com.tevfikkoseli.app.service.grpc.order.proto.IntId;
import com.tevfikkoseli.app.service.grpc.order.proto.IntPair;
import com.tevfikkoseli.app.service.grpc.order.proto.LongId;
import com.tevfikkoseli.app.service.grpc.order.proto.OrderGRPCServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.csystem.app.api.dto.OrderInfoDTO;
import org.csystem.app.api.dto.OrderProductInfoDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderAppGRPCService {
    @GrpcClient("orderGRPCService")
    private final OrderGRPCServiceGrpc.OrderGRPCServiceStub m_orderGRPCServiceStub;

    private final OrderInfoStreamObserver m_orderInfoStreamObserver;
    private final OrderProductInfoStreamObserver m_orderProductInfoStreamObserver;


    public OrderAppGRPCService(OrderGRPCServiceGrpc.OrderGRPCServiceStub orderGRPCServiceStub, OrderInfoStreamObserver orderInfoStreamObserver, OrderProductInfoStreamObserver orderProductInfoStreamObserver)
    {
        m_orderGRPCServiceStub = orderGRPCServiceStub;
        m_orderInfoStreamObserver = orderInfoStreamObserver;
        m_orderProductInfoStreamObserver = orderProductInfoStreamObserver;
    }

    public Iterable<OrderInfoDTO> findOrdersByMonthAndYear(int month, int year)
    {
        var pair = IntPair.newBuilder()
                .setFirst(month)
                .setSecond(year)
                .build();

        m_orderGRPCServiceStub.findOrdersByMonthAndYear(pair, m_orderInfoStreamObserver);

        return m_orderInfoStreamObserver.getOrderInfoList();
    }

    public Iterable<OrderInfoDTO> findOrdersClientId(int clientId)
    {
        var intId = IntId.newBuilder()
                .setId(clientId)
                .build();

        m_orderGRPCServiceStub.findOrdersByClientId(intId, m_orderInfoStreamObserver);

        return m_orderInfoStreamObserver.getOrderInfoList();
    }

    public Iterable<OrderInfoDTO> findOrdersByYearBetween(int begin, int end)
    {
        var pair = IntPair.newBuilder()
                .setFirst(begin)
                .setSecond(end)
                .build();

        m_orderGRPCServiceStub.findOrdersByYearBetween(pair, m_orderInfoStreamObserver);

        return m_orderInfoStreamObserver.getOrderInfoList();
    }


    public Iterable<OrderProductInfoDTO> findOrderProductsByOrderId(long id)
    {
        var longId = LongId.newBuilder()
                .setId(id)
                .build();

        m_orderGRPCServiceStub.findOrderProductsByOrderId(longId, m_orderProductInfoStreamObserver);

        return m_orderProductInfoStreamObserver.getOrderProductInfoList();
    }


    //...
}