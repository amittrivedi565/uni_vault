package com.univault.auth.service;

import org.springframework.stereotype.Component;

import com.univault.auth.entity.UserEntity;

@Component
public interface AuthService {
    UserEntity register(UserEntity request);
    String authenticate(UserEntity request);
}
