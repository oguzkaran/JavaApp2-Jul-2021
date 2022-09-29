package org.csystem.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * @author onder sahin
 */
@Configuration
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Value("${app.security.application_user_query}")
    private String applicationUserQuery;

    @Value("${app.security.application_user_role_query}")
    private String applicationUserRoleQuery;

    public ApplicationSecurityConfiguration(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.httpBasic().and().csrf().disable().formLogin().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(applicationUserQuery)
                .authoritiesByUsernameQuery(applicationUserRoleQuery);
    }
}
