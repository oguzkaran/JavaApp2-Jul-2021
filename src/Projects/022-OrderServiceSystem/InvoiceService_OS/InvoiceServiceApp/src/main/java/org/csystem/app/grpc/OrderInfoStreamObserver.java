package org.csystem.app.grpc;

import com.tevfikkoseli.app.service.grpc.order.proto.OrderInfo;
import io.grpc.stub.StreamObserver;
import org.csystem.app.api.dto.OrderInfoDTO;
import org.csystem.app.api.mapper.IOrderInfoMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class OrderInfoStreamObserver extends StreamObserverToList<OrderInfo, OrderInfoDTO> {
    private final IOrderInfoMapper m_orderInfoMapper;

    public OrderInfoStreamObserver(List<OrderInfoDTO> orderInfoList, IOrderInfoMapper orderInfoMapper)
    {
        super(orderInfoList);
        m_orderInfoMapper = orderInfoMapper;
    }


    @Override
    public void onNext(OrderInfo orderInfo)
    {
        list.add(m_orderInfoMapper.apply(orderInfo));
    }

    @Override
    public void onError(Throwable throwable)
    {

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
