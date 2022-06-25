package org.csystem.app.auth.service;

import org.csystem.app.auth.data.ApplicationUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author onder sahin
 */

@Service
public class AuthenticationService implements AuthenticationProvider {

    private final ApplicationUserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(ApplicationUserDetailsService userDetailsService, PasswordEncoder passwordEncoder)
    {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        return ensurePassword(userDetailsService.loadUserByUsername(authentication.getName()),authentication.getCredentials().toString(),passwordEncoder);
    }

    private Authentication ensurePassword(ApplicationUserDetails applicationUserDetails, String password, PasswordEncoder passwordEncoder)
    {
        if (!passwordEncoder.matches(password, applicationUserDetails.getPassword()))
            throw new BadCredentialsException("Username or password is bad");

        return new UsernamePasswordAuthenticationToken(applicationUserDetails.getUsername(), applicationUserDetails.getPassword(),applicationUserDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication)
    {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
