package org.csystem.app.api.invoice.controller.mapper;

import org.csystem.app.api.invoice.controller.dto.InvoiceSaveRequestDTO;
import org.csystem.app.api.invoice.domain.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author onder sahin
 */
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL
)
public abstract class InvoiceMapper {

    public abstract Invoice toEntity(InvoiceSaveRequestDTO dto);

    public abstract Invoice updateEntity(InvoiceSaveRequestDTO dto, @MappingTarget Invoice entity);
}
