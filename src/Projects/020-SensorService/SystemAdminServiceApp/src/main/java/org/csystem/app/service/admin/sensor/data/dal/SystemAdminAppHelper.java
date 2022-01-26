package org.csystem.app.service.admin.sensor.data.dal;

import org.csystem.app.service.admin.sensor.data.entity.Member;
import org.csystem.app.service.admin.sensor.data.repository.IMemberRepository;
import org.csystem.app.service.admin.sensor.data.repository.IMemberRoleRepository;
import org.springframework.stereotype.Component;

import static org.csystem.util.data.DatabaseUtil.doWorkForRepository;


@Component
public class SystemAdminAppHelper {
    private final IMemberRepository m_memberRepository;
    private final IMemberRoleRepository m_memberRoleRepository;

    public SystemAdminAppHelper(IMemberRepository memberRepository, IMemberRoleRepository memberRoleRepository)
    {
        m_memberRepository = memberRepository;
        m_memberRoleRepository = memberRoleRepository;
    }

    public Member saveMember(Member member)
    {
        return doWorkForRepository(() -> m_memberRepository.save(member), "SystemAdminAppHelper.saveMember");
    }
    //...
}
