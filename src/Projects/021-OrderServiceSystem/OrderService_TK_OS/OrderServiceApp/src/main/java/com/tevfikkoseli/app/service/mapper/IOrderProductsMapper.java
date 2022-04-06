package com.tevfikkoseli.app.service.mapper;


import com.tevfikkoseli.app.service.data.dto.OrderProductsDTO;
import com.tevfikkoseli.app.service.data.entity.OrderProducts;
import org.mapstruct.Mapper;

@Mapper(implementationName = "OrderProductsMapper", componentModel = "spring")
public interface IOrderProductsMapper {

    OrderProductsDTO toOrderProductsDTO (OrderProducts orderProducts);
    OrderProducts toOrderProducts ( OrderProductsDTO orderProductsDTO);
}
