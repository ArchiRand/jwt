package com.archirand.jwt.repository.role;

import com.archirand.jwt.InMemoryDatabase;
import com.archirand.jwt.model.Role;
import com.archirand.jwt.model.User;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RoleRepositoryImpl implements RoleRepository {

    @Override
    public List<Role> findByUser(@NonNull User user) {
        return InMemoryDatabase.instance()
                .getRoles()
                .stream()
                .filter(role -> role.getUsers().contains(user))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Role> findByName(@NonNull String roleName) {
        return InMemoryDatabase.instance()
                .getRoles()
                .stream()
                .filter(role -> role.getName().toUpperCase().equals(roleName.toUpperCase()))
                .findFirst();
    }
}
