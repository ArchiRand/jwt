package com.archirand.jwt.repository.user;

import com.archirand.jwt.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Optional<User> findByLogin(String login);

    Optional<User> findById(Long id);
}
