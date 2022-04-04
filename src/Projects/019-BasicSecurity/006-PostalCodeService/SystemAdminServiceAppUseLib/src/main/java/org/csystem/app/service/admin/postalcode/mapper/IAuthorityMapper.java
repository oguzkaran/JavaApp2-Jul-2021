package org.csystem.app.service.admin.postalcode.mapper;


import org.csystem.app.service.admin.postalcode.dto.AuthoritySaveDTO;
import org.csystem.util.security.web.data.entity.Authority;
import org.mapstruct.Mapper;

@Mapper(implementationName = "MemberRoleMapperImpl", componentModel = "spring")
public interface IAuthorityMapper {
    Authority toAuthority(AuthoritySaveDTO memberRoleSaveDTO);
}
