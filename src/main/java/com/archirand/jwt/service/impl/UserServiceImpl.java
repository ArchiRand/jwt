package com.archirand.jwt.service.impl;

import com.archirand.jwt.model.User;
import com.archirand.jwt.repository.user.UserRepository;
import com.archirand.jwt.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = userRepository.findAll();
        if (allUsers == null || allUsers.isEmpty()) {
            log.warn("No users found");
        } else {
            return allUsers;
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<User> findByLogin(String login) {
        Optional<User> optionalUser = userRepository.findByLogin(login);
        if (optionalUser.isPresent()) {
            return optionalUser;
        } else {
            log.warn("User with login " + login + " wasn't found");
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser;
        } else {
            log.warn("User with id " + id + " wasn't found");
        }
        return Optional.empty();
    }
}
