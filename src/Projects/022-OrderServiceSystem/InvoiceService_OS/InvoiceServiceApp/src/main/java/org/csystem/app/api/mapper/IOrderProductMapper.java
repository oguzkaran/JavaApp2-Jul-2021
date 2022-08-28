package org.csystem.app.api.mapper;

import com.tevfikkoseli.app.service.grpc.order.proto.OrderProductInfo;
import org.csystem.app.api.dto.OrderProductInfoDTO;
import org.mapstruct.Mapper;

@Mapper(implementationName = "OrderProductMapperImpl", componentModel = "spring")
public interface IOrderProductMapper extends IMapper<OrderProductInfo, OrderProductInfoDTO> {
    OrderProductInfoDTO toOrderProductDTO(OrderProductInfo orderInfo);
}
