package com.archirand.jwt.repository.role;

import com.archirand.jwt.model.Role;
import com.archirand.jwt.model.User;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {

    List<Role> findByUser(User user);

    Optional<Role> findByName(String roleName);
}
