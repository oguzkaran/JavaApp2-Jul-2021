package org.csystem.app.api.customer.controller.mapper;

import org.csystem.app.api.customer.controller.dto.CustomerSaveRequestDTO;
import org.csystem.app.api.customer.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author onder sahin
 */

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL
)
public abstract class CustomerMapper {

    public abstract Customer toEntity(CustomerSaveRequestDTO dto);

    public abstract Customer updateEntity(CustomerSaveRequestDTO dto, @MappingTarget Customer entity);

}
