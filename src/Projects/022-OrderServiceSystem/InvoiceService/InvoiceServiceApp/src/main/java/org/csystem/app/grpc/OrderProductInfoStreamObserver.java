package org.csystem.app.grpc;

import com.tevfikkoseli.app.service.grpc.order.proto.OrderProductInfo;
import io.grpc.stub.StreamObserver;
import org.csystem.app.api.dto.OrderProductInfoDTO;
import org.csystem.app.api.mapper.IOrderProductMapper;
import org.csystem.util.console.Console;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class OrderProductInfoStreamObserver extends StreamObserverToList<OrderProductInfo, OrderProductInfoDTO> {
    private final IOrderProductMapper m_orderProductMapper;

    public OrderProductInfoStreamObserver(List<OrderProductInfoDTO> orderInfoList, IOrderProductMapper orderProductMapper)
    {
        super(orderInfoList);
        m_orderProductMapper = orderProductMapper;
    }

    @Override
    public void onNext(OrderProductInfo orderProductInfo)
    {
        Console.writeLine(orderProductInfo.getOrderId());
        list.add(m_orderProductMapper.apply(orderProductInfo));
    }

    @Override
    public void onError(Throwable throwable)
    {
        //...
    }

    @Override
    public void onCompleted()
    {
        //...
    }

    public List<OrderProductInfoDTO> getOrderProductInfoList()
    {
        return list;
    }
}
