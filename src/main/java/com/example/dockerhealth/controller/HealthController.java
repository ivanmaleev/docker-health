package com.example.dockerhealth.controller;

import com.example.dockerhealth.dto.HealthDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Random;

@RestController
public class HealthController {

    @Value("${health.random.max}")
    private Integer randomMax;

    private final Random random = new Random();

    @GetMapping("/health")
    public ResponseEntity<HealthDto> getHealthStatus() {
        if (Objects.equals(random.nextInt(randomMax), randomMax - 1)) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(new HealthDto("OK"));
    }
}
