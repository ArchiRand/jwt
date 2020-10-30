package com.archirand.jwt.factory;

import com.archirand.jwt.model.Role;
import com.archirand.jwt.model.User;
import com.archirand.jwt.model.UserContact;
import com.archirand.jwt.model.enums.Status;
import lombok.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class PseudoEntityFactory {

    public static List<? extends User> createUsers(int amount, PasswordEncoder passwordEncoder) {
        List<User> users = new ArrayList<>();
        for (int index = 0; index < amount; index++) {
            int id = index + 1;
            User user = new User();
            user.setId(Integer.toUnsignedLong(id));
            user.setLogin("user_" + id);
            user.setPassword(passwordEncoder.encode("password_" + id));
            user.setName("Test_" + id);
            user.setLastName("Testovich_" + id);
            user.setStatus(Status.ACTIVE);

//            defineUserStatus(user, id);

            createUserContactAndLinkWithUser(user, id);

            users.add(user);
        }
        return users;
    }

    private static void defineUserStatus(User user, int id) {
        if (id % 2 == 0) {
            user.setStatus(Status.NOT_ACTIVE);
        } else if (id % 3 == 0) {
            user.setStatus(Status.DELETED);
        } else {
            user.setStatus(Status.ACTIVE);
        }
    }

    private static void createUserContactAndLinkWithUser(User user, int id) {
        UserContact userContact = new UserContact();
        userContact.setUser(user);
        userContact.setId(Integer.toUnsignedLong(id));
        userContact.setEmail(user.getLogin() + "@" + "mail.net");
        userContact.setPhoneNumber("123-45-67");
        userContact.setStatus(Status.ACTIVE);
        user.getContacts().add(userContact);
    }

    public static List<Role> createRoles(@NonNull String... roleNames) {
        AtomicLong id = new AtomicLong(1);
        return Arrays.stream(roleNames)
                .map(roleName -> createRole(roleName, id.getAndIncrement()))
                .collect(Collectors.toList());
    }

    private static Role createRole(String roleName, long id) {
        Role role = new Role();
        role.setId(id);
        role.setStatus(Status.ACTIVE);
        role.setName(roleName);
        return role;
    }

    public static void linkUserWithRoles(List<User> users, List<Role> roles) {
        users.forEach(user -> {
            long userId = user.getId();
            if (userId % 2 == 0) {
                user.getRoles().add(roles.get(0));
                roles.get(0).getUsers().add(user);
            } else if (userId % 3 == 0) {
                user.getRoles().add(roles.get(1));
                roles.get(1).getUsers().add(user);
            } else {
                user.getRoles().add(roles.get(2));
                roles.get(2).getUsers().add(user);
            }
        });
    }

}
