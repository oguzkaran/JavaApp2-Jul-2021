package org.csystem.app.service.admin.sensor.controller;

import org.csystem.app.service.admin.sensor.dto.MemberSaveDTO;
import org.csystem.app.service.admin.sensor.service.SystemAdminAppService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/members")
public class SystemAdminAppController {
    private final SystemAdminAppService m_systemAdminAppService;

    public SystemAdminAppController(SystemAdminAppService systemAdminAppService)
    {
        m_systemAdminAppService = systemAdminAppService;
    }

    @PostMapping("member/save")
    @Secured("ROLE_SYS_ADMIN")
    public MemberSaveDTO saveMember(@RequestBody MemberSaveDTO memberSaveDTO)
    {
        return m_systemAdminAppService.saveMember(memberSaveDTO);
    }
}
