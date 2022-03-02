package org.csystem.app.service.admin.sensor.data.dal;

import org.csystem.app.service.admin.sensor.data.entity.Member;
import org.csystem.app.service.admin.sensor.data.entity.MemberRole;
import org.csystem.app.service.admin.sensor.data.repository.IMemberRepository;
import org.csystem.app.service.admin.sensor.data.repository.IMemberRoleRepository;
import org.springframework.stereotype.Component;

import java.lang.management.MemoryPoolMXBean;
import java.util.Optional;

import static org.csystem.util.data.DatabaseUtil.doWorkForRepository;

@Component
public class SystemAdminAppHelper {
    private final IMemberRepository m_memberRepository;
    private final IMemberRoleRepository m_memberRoleRepository;

    private Member saveMemberCallback(Member member)
    {
        m_memberRepository.insertMember(member.username, member.password, member.enabled);

        return member;
    }

    public SystemAdminAppHelper(IMemberRepository memberRepository, IMemberRoleRepository memberRoleRepository)
    {
        m_memberRepository = memberRepository;
        m_memberRoleRepository = memberRoleRepository;
    }

    public Optional<Member> findById(int id)
    {
        return doWorkForRepository(() -> m_memberRepository.findById(id), "SystemAdminAppHelper.findById");
    }

    public Member saveMember(Member member)
    {
        return doWorkForRepository(() -> saveMemberCallback(member), "SystemAdminAppHelper.saveMember");
    }

    public MemberRole saveMemberRole(MemberRole memberRole)
    {
        return doWorkForRepository(() -> m_memberRoleRepository.save(memberRole), "SystemAdminAppHelper.saveMemberRole");
    }
    //...
}
