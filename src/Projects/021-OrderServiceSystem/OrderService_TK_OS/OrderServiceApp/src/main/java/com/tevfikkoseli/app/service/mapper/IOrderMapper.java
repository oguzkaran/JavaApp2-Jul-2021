package com.tevfikkoseli.app.service.mapper;

import com.tevfikkoseli.app.service.dto.OrderDTO;
import com.tevfikkoseli.app.service.data.entity.Order;
import org.mapstruct.Mapper;

@Mapper(implementationName = "OrderMapper", componentModel = "spring")
public interface IOrderMapper {
    OrderDTO toOrderDTO (Order order);
    Order toOrder ( OrderDTO orderDTO);
}
