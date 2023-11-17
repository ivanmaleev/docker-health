package com.example.dockerhealth.controller;

import com.example.dockerhealth.dto.UserDto;
import com.example.dockerhealth.entity.User;
import com.example.dockerhealth.repository.UserRepository;
import com.example.dockerhealth.service.UserRegisterService;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

    @Value("${health.random.max}")
    private Integer randomMax;
    private final Random random = new Random();

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRegisterService userRegisterService;

    @PostMapping("")
    public ResponseEntity<User> create(@RequestBody User user) {
        log.info(Objects.toString(user));
        if (Objects.equals(random.nextInt(randomMax), 1)) {
            return ResponseEntity.internalServerError().build();
        }
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> get(@PathVariable Long userId) {
        log.info(Objects.toString(userId));
        if (Objects.equals(random.nextInt(randomMax), 1)) {
            return ResponseEntity.internalServerError().build();
        }
        return new ResponseEntity<>(userRepository.findById(userId).orElseThrow(), HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> profile() {
        User currentUser = (User) userRegisterService.getCurrentUser();
        if (Objects.nonNull(currentUser.getId())) {
            log.info(Objects.toString(currentUser));
            return new ResponseEntity<>(currentUser, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        log.info(Objects.toString(userId));
        userRepository.deleteById(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("")
    public ResponseEntity<User> put(@RequestBody UserDto userDto) {
        log.info(Objects.toString(userDto));
        User user = userRepository.findByUsername(userDto.getUsername())
                .orElseThrow();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        user = userRepository.save(user);
        log.info(Objects.toString(user));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
