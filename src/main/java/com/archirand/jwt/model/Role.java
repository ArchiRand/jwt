package com.archirand.jwt.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {

    private String name;

    private List<User> users = new ArrayList<>();

    @Override
    public String toString() {
        return "Role is " + name;
    }
}
