package com.archirand.jwt.rest;

import com.archirand.jwt.dto.AdminUserDto;
import com.archirand.jwt.model.User;
import com.archirand.jwt.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminControllerV1 {

    private final UserService userService;

    public AdminControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "users/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id) {
        Optional<User> result = userService.findById(id);
        return result
                .map(AdminUserDto::fromUser)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }
}
