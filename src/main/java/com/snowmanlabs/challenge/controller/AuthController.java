package com.snowmanlabs.challenge.controller;

import com.snowmanlabs.challenge.dto.AuthRequestDto;
import com.snowmanlabs.challenge.dto.AuthResponseDto;
import com.snowmanlabs.challenge.service.AuthService;
import com.snowmanlabs.challenge.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final JwtService jwtService;
    private final AuthService authService;

    public AuthController(JwtService jwtService, AuthService authService) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> authenticate(@RequestBody AuthRequestDto authRequestDto) {
        var authenticatedUser = authService.authenticate(authRequestDto);
        var jwtToken = jwtService.generateToken(authenticatedUser);

        var loginResponse = AuthResponseDto.builder().token(jwtToken).expiresIn(jwtService.getExpirationTime()).build();

        return ResponseEntity.ok(loginResponse);
    }
}
