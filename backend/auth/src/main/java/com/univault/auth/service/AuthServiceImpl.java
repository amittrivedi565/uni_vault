package com.univault.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.univault.auth.dao.UserDao;
import com.univault.auth.entity.UserEntity;
import com.univault.auth.validate.AuthValidation;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private UserDao userDAO;

    @Autowired
    private AuthValidation authValidation;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserEntity register(UserEntity request) {
        logger.info("Attempting to register user: {}", request.getUsername());

        if (userExists(request.getUsername())) {
            logger.warn("Registration failed - Username already exists: {}", request.getUsername());
            throw new IllegalArgumentException("Username already exists");
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        UserEntity savedUser = userDAO.save(newUser);
        logger.info("User registered successfully: {}", savedUser.getUsername());

        return savedUser;
    }

    @Override
    public String authenticate(UserEntity request) {
        logger.info("Authenticating user: {}", request.getUsername());

        UserEntity user = userDAO.findByUsername(request.getUsername())
                .orElseThrow(() -> {
                    logger.warn("Authentication failed - User not found: {}", request.getUsername());
                    return new IllegalArgumentException("Invalid username or password");
                });

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            logger.warn("Authentication failed - Incorrect password for user: {}", request.getUsername());
            throw new IllegalArgumentException("Invalid username or password");
        }

        String token = authValidation.generateToken(user.getUsername());
        logger.info("Authentication successful - Token issued for user: {}", request.getUsername());

        return token;
    }

    private boolean userExists(String username) {
        return userDAO.findByUsername(username).isPresent();
    }
}
