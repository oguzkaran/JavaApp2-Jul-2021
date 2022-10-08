package com.tevfikkoseli.app.service.grpc;

import com.tevfikkoseli.app.service.data.dal.OrderServiceHelper;
import com.tevfikkoseli.app.service.grpc.order.proto.*;
import com.tevfikkoseli.app.service.mapper.IOrderMapper;
import com.tevfikkoseli.app.service.mapper.IOrderProductMapper;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class OrderService extends OrderGRPCServiceGrpc.OrderGRPCServiceImplBase {
    private final OrderServiceHelper m_orderServiceHelper;
    private final IOrderMapper m_orderMapper;

    private final IOrderProductMapper m_orderProductMapper;

    public OrderService(OrderServiceHelper orderServiceHelper, IOrderMapper orderMapper,
                        IOrderProductMapper orderProductMapper)
    {
        m_orderServiceHelper = orderServiceHelper;
        m_orderMapper = orderMapper;
        m_orderProductMapper = orderProductMapper;
    }


    @Override
    public void findOrderInformationByIdentity(IdentityInfo request, StreamObserver<OrderInformation> responseObserver)
    {
        super.findOrderInformationByIdentity(request, responseObserver);
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
    public void findOrdersByClientId(IntId request, StreamObserver<OrderInfo> responseObserver)//
    {
        var orders = m_orderServiceHelper.findOrdersByClientId(request.getId());

        orders.forEach(o -> responseObserver.onNext(m_orderMapper.toOrderInfo(o)));
        responseObserver.onCompleted();
    }

    @Override
    public void findOrdersByYearBetween(IntPair request, StreamObserver<OrderInfo> responseObserver)//
    {
        var begin = request.getFirst();
        var end = request.getSecond();
        var orders = m_orderServiceHelper.findOrdersByYearBetween(begin, end);

        orders.forEach(o -> responseObserver.onNext(m_orderMapper.toOrderInfo(o)));
        responseObserver.onCompleted();
    }

    ////////////// Test yapılacak metotlar. grpcurl ile test edebilirsiniz

    @Override
    public void findOrdersByProductId(IntId request, StreamObserver<OrderInfo> responseObserver) 
    {
        var orders = m_orderServiceHelper.findOrdersByProductId(request.getId());
        orders.forEach(o -> responseObserver.onNext(m_orderMapper.toOrderInfo(o)));
        responseObserver.onCompleted();
    }

    @Override
    public void findOrderProductsByOrderId(LongId request, StreamObserver<OrderProductInfo> responseObserver) 
    {
        var orderProducts = m_orderServiceHelper.findOrderProductsByOrderId(request.getId());

        orderProducts.forEach(op -> responseObserver.onNext(m_orderProductMapper.toOrderProductInfo(op)));
        responseObserver.onCompleted();
    }

    @Override
    public void findOrderProductsByProductId(IntId request, StreamObserver<OrderProductInfo> responseObserver)
    {
        var orderProducts = m_orderServiceHelper.findOrderProductsByProductId(request.getId());

        orderProducts.forEach(op -> responseObserver.onNext(m_orderProductMapper.toOrderProductInfo(op)));

        responseObserver.onCompleted();
    }
}
