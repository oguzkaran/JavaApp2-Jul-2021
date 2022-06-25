package com.tevfikkoseli.app.service.mapper;


import com.tevfikkoseli.app.service.data.entity.Order;
import com.tevfikkoseli.app.service.data.entity.OrderProduct;
import com.tevfikkoseli.app.service.grpc.order.proto.OrderInfo;
import com.tevfikkoseli.app.service.grpc.order.proto.OrderProductInfo;
import org.mapstruct.Mapper;

import java.time.format.DateTimeFormatter;

@Mapper(implementationName = "OrderProductMapperImpl", componentModel = "spring")
public interface IOrderProductMapper {

    default OrderProductInfo toOrderProductInfo(OrderProduct orderProduct)
    {
        var builder = OrderProductInfo.newBuilder();

        builder.setOrderId(orderProduct.getOrderId());
        builder.setProductId(orderProduct.getProductId());
        builder.setQuantity(orderProduct.getQuantity());
        builder.setUnitPrice(orderProduct.getUnitPrice());

        return builder.build();
    }
}
