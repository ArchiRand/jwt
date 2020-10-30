package com.archirand.jwt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {

    private static final long serialVersionUID = 338563307726391755L;

    private String name;

    @JsonIgnore
    private List<User> users = new ArrayList<>();

    @Override
    public String toString() {
        return name;
    }
}
