package org.csystem.app.service.sensor.configuration.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@Profile("test")
public class SensorServiceAppSecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource m_dataSource;

    @Value("${app.security.member_query}")
    private String m_memberQuery;

    @Value("${app.security.role_query}")
    private String m_roleQuery;

    public SensorServiceAppSecurityConfig(DataSource dataSource)
    {
        m_dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.jdbcAuthentication()
                .dataSource(m_dataSource)
                .usersByUsernameQuery(m_memberQuery)
                .authoritiesByUsernameQuery(m_roleQuery);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.httpBasic().and().formLogin().disable();
    }
}
