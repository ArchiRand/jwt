package com.archirand.jwt.security;

import com.archirand.jwt.model.User;
import com.archirand.jwt.security.jwt.JwtUserFactory;
import com.archirand.jwt.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = userService.findByLogin(login);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User with login: " + login + " not found");
        }
        return JwtUserFactory.create(user.get());
    }
}
