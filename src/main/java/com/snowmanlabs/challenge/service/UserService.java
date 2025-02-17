package com.snowmanlabs.challenge.service;

import com.snowmanlabs.challenge.dto.UserDto;
import com.snowmanlabs.challenge.exception.BusinessException;
import com.snowmanlabs.challenge.model.User;
import com.snowmanlabs.challenge.repository.UserRepository;
import com.snowmanlabs.challenge.service.interfaces.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var savedUser = userRepository.save(user);
        return new UserDto(savedUser);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new BusinessException("User not found with the email: " + email));
    }
}
