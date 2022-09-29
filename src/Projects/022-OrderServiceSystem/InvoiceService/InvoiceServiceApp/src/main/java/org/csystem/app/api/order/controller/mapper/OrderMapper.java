package org.csystem.app.api.order.controller.mapper;

import org.csystem.app.api.invoice.domain.Invoice;
import org.csystem.app.api.invoice.service.InvoiceService;
import org.csystem.app.api.order.controller.dto.OrderSaveRequestDTO;
import org.csystem.app.api.order.domain.Order;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;

/**
 * @author onder sahin
 */
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL
)
public abstract class OrderMapper {

    @Autowired
    private InvoiceService invoiceService;

    @Mapping(source = "dto.invoiceId", target = "invoice",qualifiedByName = "idToInvoice")
    public abstract Order toEntity(OrderSaveRequestDTO dto);

    @Mapping(source = "dto.invoiceId", target = "invoice",qualifiedByName = "idToInvoice")
    public abstract Order updateEntity(OrderSaveRequestDTO dto, @MappingTarget Order entity);


    @Named("idToInvoice")
    Invoice idToInvoice(Integer id)
    {
        return id == null ? null : invoiceService.findInvoiceById(id).orElseThrow(EntityNotFoundException::new);
    }

}
