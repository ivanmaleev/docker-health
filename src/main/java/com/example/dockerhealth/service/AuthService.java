package com.example.dockerhealth.service;

import com.example.dockerhealth.dto.JwtRequest;
import com.example.dockerhealth.dto.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);
}
