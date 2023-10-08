package com.example.dockerhealth.controller;

import com.example.dockerhealth.entity.User;
import com.example.dockerhealth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Value("${health.random.max}")
    private Integer randomMax;

    private final Random random = new Random();

    @Autowired
    private UserRepository userRepository;

    @PostMapping("")
    public ResponseEntity<User> create(@RequestBody User user) {
        if (Objects.equals(random.nextInt(randomMax), randomMax - 1)) {
            return ResponseEntity.internalServerError().build();
        }
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> get(@PathVariable Long userId) {
        if (Objects.equals(random.nextInt(randomMax), randomMax - 1)) {
            return ResponseEntity.internalServerError().build();
        }
        return new ResponseEntity<>(userRepository.findById(userId).orElseThrow(), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        userRepository.deleteById(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("")
    public ResponseEntity<User> put(@RequestBody User user) {
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }
}
