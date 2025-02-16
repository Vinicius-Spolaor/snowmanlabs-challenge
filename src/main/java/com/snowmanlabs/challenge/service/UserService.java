package com.snowmanlabs.challenge.service;

import com.snowmanlabs.challenge.dto.UserDto;
import com.snowmanlabs.challenge.exception.BusinessException;
import com.snowmanlabs.challenge.model.User;
import com.snowmanlabs.challenge.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto saveUser(User user) {
        var savedUser = userRepository.save(user);
        return new UserDto(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new BusinessException("User not found with the email: " + email));
    }
}
