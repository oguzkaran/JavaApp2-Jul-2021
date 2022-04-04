package org.csystem.app.service.admin.shopping.service;

import org.csystem.app.service.admin.shopping.data.dal.SystemAdminAppHelper;
import org.csystem.app.service.admin.shopping.dto.AuthoritySaveDTO;
import org.csystem.app.service.admin.shopping.dto.UserSaveDTO;
import org.csystem.app.service.admin.shopping.mapper.IUserMapper;
import org.csystem.util.security.web.data.entity.Authority;
import org.csystem.util.security.web.data.global.BeanName;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static org.csystem.util.data.DatabaseUtil.doWorkForService;

@Service
public class SystemAdminAppService {
    private final SystemAdminAppHelper m_systemAdminAppHelper;
    private final IUserMapper m_userMapper;
    private final BCryptPasswordEncoder m_bCryptPasswordEncoder;

    private UserSaveDTO saveUserCallback(UserSaveDTO userSaveDTO)
    {
        userSaveDTO.password = "{bcrypt}" + m_bCryptPasswordEncoder.encode(userSaveDTO.password);
        return m_userMapper.toUserSaveDTO(m_systemAdminAppHelper.saveUser(m_userMapper.toUser(userSaveDTO)));
    }

    private AuthoritySaveDTO saveAuthorityCallback(AuthoritySaveDTO authoritySaveDTO)
    {
        var authorityOpt = m_systemAdminAppHelper.findUserById(authoritySaveDTO.username);

        if (authorityOpt.isEmpty())
            throw new IllegalArgumentException("No user:" + authoritySaveDTO.username);

        var authority = new Authority();

        authority.authority = authoritySaveDTO.authority;
        authority.user = authorityOpt.get();

        m_systemAdminAppHelper.saveAuthority(authority);

        return authoritySaveDTO;
    }

    public SystemAdminAppService(SystemAdminAppHelper systemAdminAppHelper, IUserMapper userMapper,
                                 @Qualifier(BeanName.BCRYPT_PASSWORD_ENCODER) BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        m_systemAdminAppHelper = systemAdminAppHelper;
        m_userMapper = userMapper;
        m_bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserSaveDTO saveUser(UserSaveDTO userSaveDTO)
    {
        return doWorkForService(() -> saveUserCallback(userSaveDTO), "SystemAdminAppService.saveUser");
    }

    public AuthoritySaveDTO saveAuthority(AuthoritySaveDTO authoritySaveDTO)
    {
        return doWorkForService(() -> saveAuthorityCallback(authoritySaveDTO), "SystemAdminAppService.saveMemberRole");
    }
}
