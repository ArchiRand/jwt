package com.archirand.jwt.repository.user;

import com.archirand.jwt.InMemoryDatabase;
import com.archirand.jwt.model.User;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {

    @Override
    public List<User> findAll() {
        return InMemoryDatabase.instance().getUsers();
    }

    @Override
    public Optional<User> findByLogin(@NonNull String login) {
        return InMemoryDatabase.instance()
                .getUsers()
                .stream()
                .filter(user -> user.getLogin().toUpperCase().equals(login.toUpperCase()))
                .findFirst();
    }

    @Override
    public Optional<User> findById(Long id) {
        return InMemoryDatabase.instance()
                .getUsers()
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }
}
