package com.tevfikkoseli.app.service.mapper;


import com.tevfikkoseli.app.service.data.dto.OrderProductsDTO;
import com.tevfikkoseli.app.service.data.entity.OrderProduct;
import org.mapstruct.Mapper;

@Mapper(implementationName = "OrderProductsMapper", componentModel = "spring")
public interface IOrderProductsMapper {
    OrderProductsDTO toOrderProductsDTO (OrderProduct orderProducts);
    OrderProduct toOrderProducts (OrderProductsDTO orderProductsDTO);
}
