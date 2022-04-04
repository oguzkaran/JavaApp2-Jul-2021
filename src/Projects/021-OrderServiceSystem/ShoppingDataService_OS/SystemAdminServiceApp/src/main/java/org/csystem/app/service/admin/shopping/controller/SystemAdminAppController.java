package org.csystem.app.service.admin.shopping.controller;

import org.csystem.app.service.admin.shopping.dto.AuthoritySaveDTO;
import org.csystem.app.service.admin.shopping.dto.UserSaveDTO;
import org.csystem.app.service.admin.shopping.service.SystemAdminAppService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("api/users")
public class SystemAdminAppController {
    private final SystemAdminAppService m_systemAdminAppService;

    public SystemAdminAppController(SystemAdminAppService systemAdminAppService)
    {
        m_systemAdminAppService = systemAdminAppService;
    }

    @PostMapping("user/save")
    @RolesAllowed("SYS_ADMIN")
    public UserSaveDTO saveUser(@RequestBody UserSaveDTO userSaveDTO)
    {
        return m_systemAdminAppService.saveUser(userSaveDTO);
    }

    @PostMapping("user/authority/save")
    @RolesAllowed("SYS_ADMIN")
    public AuthoritySaveDTO saveAuthority(@RequestBody AuthoritySaveDTO authoritySaveDTO)
    {
        return m_systemAdminAppService.saveAuthority(authoritySaveDTO);
    }
}
