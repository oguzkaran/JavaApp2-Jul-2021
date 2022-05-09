package org.csystem.app.api.auth.data;

import org.csystem.app.api.auth.domain.ApplicationUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author onder sahin
 */
public class ApplicationUserDetails implements UserDetails {

    private final ApplicationUser applicationUser;

    public ApplicationUserDetails(ApplicationUser applicationUser)
    {
        this.applicationUser = applicationUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return applicationUser.getApplicationUserRoles().stream().map(applicationUserRole ->
                new SimpleGrantedAuthority(applicationUserRole.getRoleName().getName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword()
    {
        return applicationUser.getPassword();
    }

    @Override
    public String getUsername()
    {
        return applicationUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return applicationUser.isEnabled();
    }
}
