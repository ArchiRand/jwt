package com.archirand.jwt.service;

import com.archirand.jwt.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAll();

    Optional<User> findByLogin(String name);

    Optional<User> findById(Long id);
}
