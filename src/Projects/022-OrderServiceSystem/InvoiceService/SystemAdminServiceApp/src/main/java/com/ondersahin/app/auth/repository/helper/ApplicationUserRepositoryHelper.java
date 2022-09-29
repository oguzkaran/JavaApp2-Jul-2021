package com.ondersahin.app.auth.repository.helper;

import com.ondersahin.app.auth.domain.ApplicationUser;
import com.ondersahin.app.auth.domain.ApplicationUserRole;
import com.ondersahin.app.auth.repository.IApplicationUserRepository;
import com.ondersahin.app.auth.repository.IApplicationUserRoleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.csystem.util.data.DatabaseUtil.doWorkForRepository;

/**
 * @author onder sahin
 */
@Component
public class ApplicationUserRepositoryHelper {

    private final IApplicationUserRepository applicationUserRepository;
    private final IApplicationUserRoleRepository applicationUserRoleRepository;


    public ApplicationUserRepositoryHelper(IApplicationUserRepository applicationUserRepository, IApplicationUserRoleRepository applicationUserRoleRepository)
    {
        this.applicationUserRepository = applicationUserRepository;
        this.applicationUserRoleRepository = applicationUserRoleRepository;
    }

    private ApplicationUser saveApplicationUserCallback(ApplicationUser user)
    {
        return applicationUserRepository.save(user);
    }

    private ApplicationUserRole saveApplicationUserRoleCallback(ApplicationUserRole userRole)
    {
        return applicationUserRoleRepository.save(userRole);
    }


    public Optional<ApplicationUser> findApplicationUserById(int id)
    {
        return doWorkForRepository(() -> applicationUserRepository.findById(id), "ApplicationUserRepositoryHelper.findApplicationUserById");
    }

    public Optional<ApplicationUserRole> findApplicationUserRoleById(int id)
    {
        return doWorkForRepository(() -> applicationUserRoleRepository.findById(id), "ApplicationUserRepositoryHelper.findApplicationUserRoleById");
    }


    public ApplicationUser saveApplicationUser(ApplicationUser user)
    {
        return doWorkForRepository(() -> saveApplicationUserCallback(user), "ApplicationUserRepositoryHelper.saveApplicationUser");
    }

    public ApplicationUserRole saveApplicationUserRole(ApplicationUserRole userRole)
    {
        return doWorkForRepository(() -> saveApplicationUserRoleCallback(userRole), "ApplicationUserRepositoryHelper.saveApplicationUserRole");
    }

}
