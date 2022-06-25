package com.tevfikkoseli.app.service.mapper;

import com.tevfikkoseli.app.service.data.entity.Order;
import com.tevfikkoseli.app.service.grpc.order.proto.OrderInfo;
import org.mapstruct.Mapper;

import java.time.format.DateTimeFormatter;

@Mapper(implementationName = "OrderMapperImpl", componentModel = "spring")
public interface IOrderMapper {
    default OrderInfo toOrderInfo(Order order)
    {
        var builder = OrderInfo.newBuilder();

        builder.setClientId(order.getClientId());
        builder.setDatetime("");
        order.getoDateTime().ifPresent(dt -> builder.setDatetime(DateTimeFormatter.ISO_LOCAL_DATE.format(dt)));

        return builder.build();
    }
}
