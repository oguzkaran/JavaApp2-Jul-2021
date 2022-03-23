package org.csystem.app.api.auth.service;

import org.csystem.app.api.auth.data.ApplicationUserDetails;
import org.csystem.app.api.auth.domain.ApplicationUser;
import org.csystem.app.api.auth.repository.IApplicationUserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author onder sahin
 */
@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    private IApplicationUserRepository applicationUserRepository;

    public ApplicationUserDetailsService(IApplicationUserRepository applicationUserRepository)
    {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public ApplicationUserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        ApplicationUser applicationUser = applicationUserRepository.findApplicationUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("The user %s is not found !", username)));
        return new ApplicationUserDetails(applicationUser);
    }

}
