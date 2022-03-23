package org.csystem.application.server.randompasswordgenerator.mapper;


import org.csystem.application.server.randompasswordgenerator.data.dto.OrderProductsDTO;
import org.csystem.application.server.randompasswordgenerator.data.entity.OrderProducts;
import org.mapstruct.Mapper;

@Mapper(implementationName = "OrderProductsMapper", componentModel = "spring")
public interface IOrderProductsMapper {

    OrderProductsDTO toOrderProductsDTO (OrderProducts orderProducts);
    OrderProducts toOrderProducts ( OrderProductsDTO orderProductsDTO);
}
