package com.archirand.jwt.security.jwt;

import com.archirand.jwt.model.Role;
import com.archirand.jwt.model.User;
import com.archirand.jwt.model.enums.Status;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getLogin(),
                user.getPassword(),
                user.getStatus().equals(Status.ACTIVE),
                new Date(),
                user.getContacts(),
                rolesToGrantedAuthority(user.getRoles())
        );
    }

    private static List<GrantedAuthority> rolesToGrantedAuthority(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
