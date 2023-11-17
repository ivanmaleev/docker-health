package com.example.dockerhealth.controller;

import com.example.dockerhealth.dto.JwtRequest;
import com.example.dockerhealth.dto.JwtResponse;
import com.example.dockerhealth.dto.UserDto;
import com.example.dockerhealth.entity.User;
import com.example.dockerhealth.repository.UserRepository;
import com.example.dockerhealth.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody final JwtRequest loginRequest) {
        log.info(Objects.toString(loginRequest));
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public UserDto register(@RequestBody final UserDto userDto) {
        log.info(Objects.toString(userDto));
        User user = new User(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User createdUser = userRepository.save(user);
        log.info(Objects.toString(createdUser));
        return new UserDto(createdUser);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody final String refreshToken) {
        return authService.refresh(refreshToken);
    }

}