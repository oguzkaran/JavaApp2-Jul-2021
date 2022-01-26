package org.csystem.app.service.admin.sensor.service;

import org.csystem.app.service.admin.sensor.data.dal.SystemAdminAppHelper;
import org.csystem.app.service.admin.sensor.dto.MemberSaveDTO;
import org.csystem.app.service.admin.sensor.mapper.IMemberMapper;
import org.springframework.stereotype.Service;

import static org.csystem.util.data.DatabaseUtil.doWorkForService;

@Service
public class SystemAdminAppService {
    private final SystemAdminAppHelper m_systemAdminAppHelper;
    private final IMemberMapper m_memberMapper;

    private MemberSaveDTO saveMemberCallback(MemberSaveDTO memberSaveDTO)
    {
        m_systemAdminAppHelper.saveMember(m_memberMapper.toMember(memberSaveDTO));

        return memberSaveDTO;
    }

    public SystemAdminAppService(SystemAdminAppHelper systemAdminAppHelper, IMemberMapper memberMapper)
    {
        m_systemAdminAppHelper = systemAdminAppHelper;
        m_memberMapper = memberMapper;
    }

    public MemberSaveDTO saveMember(MemberSaveDTO memberSaveDTO)
    {
        return doWorkForService(() -> saveMemberCallback(memberSaveDTO), "SystemAdminAppService.saveMember");
    }
}
