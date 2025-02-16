package com.snowmanlabs.challenge.service;

import com.snowmanlabs.challenge.dto.UserDto;
import com.snowmanlabs.challenge.model.User;

public interface IUserService {
    UserDto saveUser(User user);
    User findByEmail(String email);
}
