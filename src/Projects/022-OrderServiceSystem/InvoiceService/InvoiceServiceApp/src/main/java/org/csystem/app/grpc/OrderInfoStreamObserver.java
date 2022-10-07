package org.csystem.app.grpc;

import com.tevfikkoseli.app.service.grpc.order.proto.OrderInfo;
import io.grpc.stub.StreamObserver;
import org.csystem.app.api.dto.OrderInfoDTO;
import org.csystem.app.api.mapper.IOrderInfoMapper;
import org.csystem.app.api.order.repository.IOrderRepository;
import org.csystem.util.console.Console;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class OrderInfoStreamObserver extends StreamObserverToList<OrderInfo, OrderInfoDTO> {
    private final IOrderInfoMapper m_orderInfoMapper;
    private final IOrderRepository m_orderRepository;

    public OrderInfoStreamObserver(List<OrderInfoDTO> orderInfoList, IOrderInfoMapper orderInfoMapper,
                                   IOrderRepository orderRepository)
    {
        super(orderInfoList);
        m_orderInfoMapper = orderInfoMapper;
        m_orderRepository = orderRepository;
    }


    @Override
    public void onNext(OrderInfo orderInfo)
    {
        Console.writeLine(orderInfo.getClientId());
        list.add(m_orderInfoMapper.apply(orderInfo));
    }

    @Override
    public void onError(Throwable ex)
    {
        ex.printStackTrace();
    }

    @Override
    public void onCompleted()
    {

    }

    public List<OrderInfoDTO> getOrderInfoList()
    {
        return list;
    }
}
