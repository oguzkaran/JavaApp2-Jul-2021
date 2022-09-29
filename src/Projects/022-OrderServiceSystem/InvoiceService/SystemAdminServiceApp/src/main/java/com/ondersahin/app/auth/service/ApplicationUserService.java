package com.ondersahin.app.auth.service;

import com.ondersahin.app.auth.repository.helper.ApplicationUserRepositoryHelper;
import com.ondersahin.app.auth.domain.ApplicationUser;
import com.ondersahin.app.auth.domain.ApplicationUserRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.csystem.util.data.DatabaseUtil.doWorkForService;

/**
 * @author onder sahin
 */

@Service
public class ApplicationUserService {

    private final ApplicationUserRepositoryHelper applicationUserRepositoryHelper;
    private final PasswordEncoder passwordEncoder;

    public ApplicationUserService(ApplicationUserRepositoryHelper applicationUserRepositoryHelper,PasswordEncoder passwordEncoder)
    {
        this.applicationUserRepositoryHelper = applicationUserRepositoryHelper;
        this.passwordEncoder = passwordEncoder;
    }

    private ApplicationUser saveApplicationUserCallback(ApplicationUser user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return applicationUserRepositoryHelper.saveApplicationUser(user);
    }

    private ApplicationUserRole saveApplicationUserRoleCallback(ApplicationUserRole userRole)
    {
        return applicationUserRepositoryHelper.saveApplicationUserRole(userRole);
    }

    public Optional<ApplicationUser> findById(int id)
    {
        return applicationUserRepositoryHelper.findApplicationUserById(id);
    }

    public ApplicationUser saveApplicationUser(ApplicationUser user)
    {
        return doWorkForService(() -> saveApplicationUserCallback(user), "ApplicationUserService.saveApplicationUser");
    }

    public ApplicationUserRole saveApplicationUserRole(ApplicationUserRole userRole)
    {
        return doWorkForService(() -> saveApplicationUserRoleCallback(userRole), "ApplicationUserService.saveApplicationUserRole");
    }

}
