package org.csystem.app.api.customer.controller.mapper;

import org.csystem.app.api.customer.controller.dto.CustomerPhoneSaveRequestDTO;
import org.csystem.app.api.customer.controller.dto.CustomerSaveRequestDTO;
import org.csystem.app.api.customer.domain.Customer;
import org.csystem.app.api.customer.domain.Phone;
import org.csystem.app.api.customer.domain.PhoneType;
import org.csystem.app.api.customer.service.CustomerService;
import org.csystem.app.api.customer.service.PhoneService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;

/**
 * @author onder sahin
 */
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL
)
public abstract class CustomerPhoneMapper {

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private CustomerService customerService;

    @Mapping(source = "dto.customerId", target = "customer",qualifiedByName = "idToCustomer")
    @Mapping(source = "dto.phoneTypeId", target = "phoneType",qualifiedByName = "idToPhoneType")
    public abstract Phone toEntity(CustomerPhoneSaveRequestDTO dto);

    @Mapping(source = "dto.customerId", target = "customer",qualifiedByName = "idToCustomer")
    @Mapping(source = "dto.phoneTypeId", target = "phoneType",qualifiedByName = "idToPhoneType")
    public abstract Phone updateEntity(CustomerPhoneSaveRequestDTO dto, @MappingTarget Phone entity);


    @Named("idToCustomer")
    Customer idToCustomer(Integer id)
    {
        return id == null ? null : customerService.findCustomerById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Named("idToPhoneType")
    PhoneType idToPhoneType(Integer id)
    {
        return id == null ? null : phoneService.findPhoneTypeById(id).orElseThrow(EntityNotFoundException::new);
    }
}
