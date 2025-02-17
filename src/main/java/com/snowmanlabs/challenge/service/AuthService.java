package com.snowmanlabs.challenge.service;

import com.snowmanlabs.challenge.dto.AuthRequestDto;
import com.snowmanlabs.challenge.exception.BusinessException;
import com.snowmanlabs.challenge.model.User;
import com.snowmanlabs.challenge.repository.UserRepository;
import com.snowmanlabs.challenge.service.interfaces.IAuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    public User authenticate(AuthRequestDto authRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDto.getEmail(),
                        authRequestDto.getPassword()
                )
        );

        return userRepository.findByEmail(authRequestDto.getEmail())
                .orElseThrow(() -> new BusinessException("User not found with the email: " + authRequestDto.getEmail()));
    }
}
