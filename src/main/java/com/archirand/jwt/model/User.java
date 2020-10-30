package com.archirand.jwt.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    private static final long serialVersionUID = 7356990944177713077L;

    private String name;

    private String lastName;

    private String login;

    private String password;

    private List<UserContact> contacts = new ArrayList<>();

    private List<Role> roles = new ArrayList<>();

    @Override
    public String toString() {
        return "User is " + name + " " + lastName;
    }
}
