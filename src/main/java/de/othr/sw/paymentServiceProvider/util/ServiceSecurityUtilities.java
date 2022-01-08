package de.othr.sw.paymentServiceProvider.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

@Configuration
public class ServiceSecurityUtilities {
    //@Value("#{environment.USER_PASSWORD_SALT}")
    @Value("${application-config.user-password-salt}")
    private String salt;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(15, new SecureRandom(salt.getBytes()));
    }
}