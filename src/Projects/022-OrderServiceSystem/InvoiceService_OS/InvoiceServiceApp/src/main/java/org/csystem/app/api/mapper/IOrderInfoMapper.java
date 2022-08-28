package org.csystem.app.api.mapper;

import com.tevfikkoseli.app.service.grpc.order.proto.OrderInfo;
import org.csystem.app.api.dto.OrderInfoDTO;
import org.mapstruct.Mapper;

@Mapper(implementationName = "OrderInfoMapperImpl", componentModel = "spring")
public interface IOrderInfoMapper extends IMapper<OrderInfo, OrderInfoDTO> {
}
