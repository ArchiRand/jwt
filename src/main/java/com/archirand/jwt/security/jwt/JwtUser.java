package com.archirand.jwt.security.jwt;

import com.archirand.jwt.model.UserContact;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
public class JwtUser implements UserDetails {

    private static final long serialVersionUID = 1447791531607998915L;

    @JsonIgnore
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String login;
    @JsonIgnore
    private final String password;
    private final boolean enabled;
    private final Date lastPasswordResetDate;
    private final List<UserContact> contacts;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(Long id,
                   String firstName,
                   String lastName,
                   String login,
                   String password,
                   boolean enabled,
                   Date lastPasswordResetDate,
                   List<UserContact> contacts,
                   Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.contacts = contacts;
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
