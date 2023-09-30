package com.example.dockerhealth.repository;

import com.example.dockerhealth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
