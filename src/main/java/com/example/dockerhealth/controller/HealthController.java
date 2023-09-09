package com.example.dockerhealth.controller;

import com.example.dockerhealth.dto.HealthDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<HealthDto> getHealthStatus() {
        return ResponseEntity.ok(new HealthDto("OK"));
    }
}
