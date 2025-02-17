package com.snowmanlabs.challenge.controller;

import com.snowmanlabs.challenge.dto.UserDto;
import com.snowmanlabs.challenge.model.User;
import com.snowmanlabs.challenge.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public UserDto createUser(@RequestBody @Valid UserDto userDTO) {
        return userService.saveUser(new User(userDTO));
    }
}
