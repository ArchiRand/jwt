package com.archirand.jwt;

import com.archirand.jwt.factory.PseudoEntityFactory;
import com.archirand.jwt.model.Role;
import com.archirand.jwt.model.User;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class InMemoryDatabase {

    private static InMemoryDatabase database;

    @Getter
    private final List<User> users = new CopyOnWriteArrayList<>();
    @Getter
    private final List<Role> roles = new CopyOnWriteArrayList<>();

    private InMemoryDatabase() {
    }

    public static InMemoryDatabase instance() {
        InMemoryDatabase localDatabase = database;
        if (localDatabase == null) {
            synchronized (InMemoryDatabase.class) {
                localDatabase = database;
                if (localDatabase == null) {
                    database = new InMemoryDatabase();
                }
            }
        }
        return database;
    }

    public void createUsersAndRoles(int amount, PasswordEncoder passwordEncoder) {
        users.addAll(PseudoEntityFactory.createUsers(amount, passwordEncoder));
        roles.addAll(PseudoEntityFactory.createRoles("USER", "ADMIN", "UNKNOWN"));
        PseudoEntityFactory.linkUserWithRoles(users, roles);
    }

}
