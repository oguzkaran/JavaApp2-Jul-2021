package org.csystem.app.service.admin.sensor.service;

import org.csystem.app.service.admin.sensor.data.dal.SystemAdminAppHelper;
import org.csystem.app.service.admin.sensor.dto.MemberSaveDTO;
import org.csystem.app.service.admin.sensor.mapper.IMemberMapper;
import org.csystem.util.console.Console;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static org.csystem.util.data.DatabaseUtil.doWorkForService;

@Service
public class SystemAdminAppService {
    private final SystemAdminAppHelper m_systemAdminAppHelper;
    private final IMemberMapper m_memberMapper;
    private final BCryptPasswordEncoder m_bCryptPasswordEncoder;

    private MemberSaveDTO saveMemberCallback(MemberSaveDTO memberSaveDTO)
    {
        memberSaveDTO.password = "{bcrypt}" + m_bCryptPasswordEncoder.encode(memberSaveDTO.password);
        return m_memberMapper.toMemberSaveDTO(m_systemAdminAppHelper.saveMember(m_memberMapper.toMember(memberSaveDTO)));
    }

    public SystemAdminAppService(SystemAdminAppHelper systemAdminAppHelper, IMemberMapper memberMapper, BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        m_systemAdminAppHelper = systemAdminAppHelper;
        m_memberMapper = memberMapper;
        m_bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public MemberSaveDTO saveMember(MemberSaveDTO memberSaveDTO)
    {
        return doWorkForService(() -> saveMemberCallback(memberSaveDTO), "SystemAdminAppService.saveMember");
    }
}
