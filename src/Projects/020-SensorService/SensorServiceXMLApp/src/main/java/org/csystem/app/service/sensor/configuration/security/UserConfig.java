package org.csystem.app.service.sensor.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile("demo")
public class UserConfig {
    @Bean
    public List<UserDetails> getUsers()
    {
        var users = new ArrayList<UserDetails>();

        users.add(User.withDefaultPasswordEncoder()
                .username("csd").password("csd1993")
                .roles("ADMIN", "SYSTEM", "")
                .build());

        users.add(User.withDefaultPasswordEncoder()
                .username("oguz").password("csd")
                .roles("ADMIN", "SYSTEM", "")
                .build());



        return users;
    }
}
