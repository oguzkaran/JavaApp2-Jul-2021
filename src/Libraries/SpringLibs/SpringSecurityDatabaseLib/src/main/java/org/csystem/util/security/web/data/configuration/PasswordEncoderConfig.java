package org.csystem.util.security.web.data.configuration;

import org.csystem.util.security.web.data.global.BeanName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderConfig {
    @Bean(BeanName.BCRYPT_PASSWORD_ENCODER)
    @Lazy
    public BCryptPasswordEncoder getBCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
