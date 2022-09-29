package com.ondersahin.app.auth.controller.mapper;

import com.ondersahin.app.auth.controller.dto.ApplicationUserSaveRequestDTO;
import com.ondersahin.app.auth.domain.ApplicationUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


/**
 * @author onder sahin
 */
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL
)
public abstract class ApplicationUserMapper {

    public abstract ApplicationUser toEntity(ApplicationUserSaveRequestDTO dto);

    public abstract ApplicationUser updateEntity(ApplicationUserSaveRequestDTO dto, @MappingTarget ApplicationUser entity);

}
