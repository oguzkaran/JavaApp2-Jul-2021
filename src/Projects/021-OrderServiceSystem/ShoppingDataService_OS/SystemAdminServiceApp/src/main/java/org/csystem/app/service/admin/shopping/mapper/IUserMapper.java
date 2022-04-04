package org.csystem.app.service.admin.shopping.mapper;


import org.csystem.app.service.admin.shopping.dto.UserSaveDTO;
import org.csystem.util.security.web.data.entity.User;
import org.mapstruct.Mapper;

@Mapper(implementationName = "MemberMapperImpl", componentModel = "spring")
public interface IUserMapper {
    User toUser(UserSaveDTO memberSaveDTO);
    UserSaveDTO toUserSaveDTO(User user);
}
