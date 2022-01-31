package org.csystem.app.service.sensor.configuration.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Configuration
@Profile("demo")
public class SensorServiceAppSecurityDemoConfig extends WebSecurityConfigurerAdapter {
    private final List<UserDetails> m_users;

    public SensorServiceAppSecurityDemoConfig(List<UserDetails> users)
    {
        m_users = users;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        for (var user : m_users)
            auth.inMemoryAuthentication().withUser(user);
    }
}
