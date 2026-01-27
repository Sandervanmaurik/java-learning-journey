package com.sander.learningjourney.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;

@TestConfiguration
public class SpringSecurityWebTestConfig {
    
    @Bean(name = "userDetailsService")
    @Primary
    public UserDetailsService userDetailsService() {
        return new CustomInMemoryUserDetailsService();
    }
}
