package com.tevfikkoseli.app.service.grpc;

import com.tevfikkoseli.app.service.data.dal.OrderServiceHelper;
import com.tevfikkoseli.app.service.grpc.order.proto.IntId;
import com.tevfikkoseli.app.service.grpc.order.proto.IntPair;
import com.tevfikkoseli.app.service.grpc.order.proto.OrderGRPCServiceGrpc;
import com.tevfikkoseli.app.service.grpc.order.proto.OrderInfo;
import com.tevfikkoseli.app.service.mapper.IOrderMapper;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class OrderService extends OrderGRPCServiceGrpc.OrderGRPCServiceImplBase {
    private final OrderServiceHelper m_orderServiceHelper;
    private final IOrderMapper m_orderMapper;

    public OrderService(OrderServiceHelper orderServiceHelper, IOrderMapper orderMapper)
    {
        m_orderServiceHelper = orderServiceHelper;
        m_orderMapper = orderMapper;
    }

    @Override
    public void findOrdersByMonthAndYear(IntPair request, StreamObserver<OrderInfo> responseObserver)
    {
        var month = request.getFirst();
        var year = request.getSecond();

        var orders = m_orderServiceHelper.findOrdersByMonthAndYear(month, year);

        orders.forEach(o -> responseObserver.onNext(m_orderMapper.toOrderInfo(o)));
        responseObserver.onCompleted();
    }

    @Override
    public void findOrdersByClientId(IntId request, StreamObserver<OrderInfo> responseObserver)
    {
        var orders = m_orderServiceHelper.findOrdersByClientId(request.getId());

        orders.forEach(o -> responseObserver.onNext(m_orderMapper.toOrderInfo(o)));
        responseObserver.onCompleted();
    }

    @Override
    public void findOrdersByYearBetween(IntPair request, StreamObserver<OrderInfo> responseObserver)
    {
        var begin = request.getFirst();
        var end = request.getSecond();

        var orders = m_orderServiceHelper.findOrdersByYearBetween(begin, end);

        orders.forEach(o -> responseObserver.onNext(m_orderMapper.toOrderInfo(o)));
        responseObserver.onCompleted();
    }
}
