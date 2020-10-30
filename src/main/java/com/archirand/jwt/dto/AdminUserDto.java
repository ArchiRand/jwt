package com.archirand.jwt.dto;

import com.archirand.jwt.model.User;
import com.archirand.jwt.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDto {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String status;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setLogin(username);
        user.setName(firstName);
        user.setLastName(lastName);
        user.setStatus(Status.valueOf(status));
        return user;
    }

    public static AdminUserDto fromUser(User user) {
        AdminUserDto adminUserDto = new AdminUserDto();
        adminUserDto.setId(user.getId());
        adminUserDto.setUsername(user.getLogin());
        adminUserDto.setFirstName(user.getName());
        adminUserDto.setLastName(user.getLastName());
        adminUserDto.setStatus(user.getStatus().name());
        return adminUserDto;
    }
}