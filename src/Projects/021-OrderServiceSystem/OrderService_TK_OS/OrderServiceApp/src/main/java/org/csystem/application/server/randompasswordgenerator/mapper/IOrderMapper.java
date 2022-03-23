package org.csystem.application.server.randompasswordgenerator.mapper;

import org.csystem.application.server.randompasswordgenerator.data.dto.OrderDTO;
import org.csystem.application.server.randompasswordgenerator.data.entity.Order;
import org.mapstruct.Mapper;

@Mapper(implementationName = "OrderMapper", componentModel = "spring")
public interface IOrderMapper {

    OrderDTO toOrderDTO (Order order);
    Order toOrder ( OrderDTO orderDTO);


}
