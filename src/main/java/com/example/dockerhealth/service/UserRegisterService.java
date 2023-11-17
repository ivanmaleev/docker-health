package com.example.dockerhealth.service;

import com.example.dockerhealth.dto.UserDetails;
import com.example.dockerhealth.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserRegisterService {

    public Object getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ("anonymousUser" .equals(principal) || Objects.isNull(principal)) {
            return new User();
        }
        UserDetails userDetails = (UserDetails) principal;
        return userDetails.getUser();
    }
}
