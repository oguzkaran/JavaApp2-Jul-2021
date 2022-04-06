package com.ondersahin.app.auth.controller.mapper;

import com.ondersahin.app.auth.controller.dto.ApplicationUserRoleSaveRequestDTO;
import com.ondersahin.app.auth.domain.ApplicationUser;
import com.ondersahin.app.auth.service.ApplicationUserService;
import com.ondersahin.app.auth.domain.ApplicationUserRole;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;

/**
 * @author onder sahin
 */
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL
)
public abstract class ApplicationUserRoleMapper {

    @Autowired
    private ApplicationUserService applicationUserService;

    @Mapping(source = "dto.applicationUser", target = "applicationUser",qualifiedByName = "idToApplicationUser")
    public abstract ApplicationUserRole toEntity(ApplicationUserRoleSaveRequestDTO dto);

    @Mapping(source = "dto.applicationUser", target = "applicationUser",qualifiedByName = "idToApplicationUser")
    public abstract ApplicationUserRole updateEntity(ApplicationUserRoleSaveRequestDTO dto, @MappingTarget ApplicationUserRole entity);

    @Named("idToApplicationUser")
    ApplicationUser idToApplicationUser(Integer id)
    {
        return id == null ? null : applicationUserService.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
