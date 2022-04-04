package org.csystem.app.service.admin.shopping.mapper;


import org.csystem.app.service.admin.shopping.dto.AuthoritySaveDTO;
import org.csystem.util.security.web.data.entity.Authority;
import org.mapstruct.Mapper;

@Mapper(implementationName = "MemberRoleMapperImpl", componentModel = "spring")
public interface IAuthorityMapper {
    Authority toUserRole(AuthoritySaveDTO memberRoleSaveDTO);
}
