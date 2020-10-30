package com.archirand.jwt.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserContact extends BaseEntity {

    private static final long serialVersionUID = 4234966065842857577L;

    private User user;

    private String email;

    private String phoneNumber;

    @Override
    public String toString() {
        return "UserContact for user " + user.getName() + ": email is " + email + ", phone is " + phoneNumber;
    }
}
