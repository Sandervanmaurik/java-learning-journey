package com.sander.learningjourney.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomInMemoryUserDetailsService implements UserDetailsService {

    private List<GrantedAuthority> userAuthorities = new ArrayList<>(
            List.of(new SimpleGrantedAuthority("ROLE_USER")));

    private List<GrantedAuthority> adminAuthorities = new ArrayList<>(
            List.of(new SimpleGrantedAuthority("ROLE_USER"),
                    new SimpleGrantedAuthority("ROLE_ADMIN")));

    private Set<User> users = new HashSet<>(Arrays.asList(
            new User("user", "password", userAuthorities),
            new User("admin", "adminpassword", adminAuthorities)));

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var u = users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return u;
    }
}
