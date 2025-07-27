package com.univault.cas;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtService jwtService;

    private final BCryptPasswordEncoder password_encoder =
        new BCryptPasswordEncoder();

    public UserEntity register(UserType request) {
        Optional<UserEntity> optionalUser = userRepo.findByUsername(request.getUsername());

        if (optionalUser.isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(password_encoder.encode(request.getPassword()));

        return userRepo.save(user);
    }

    public String authenticate(UserType request) {
        try {
            Optional<UserEntity> optionalUser = userRepo.findByUsername(
                request.getUsername()
            );

            if (optionalUser.isEmpty()) {
                throw new RuntimeException("User not found");
            }

            UserEntity user = optionalUser.get();

            if (
                !password_encoder.matches(
                    request.getPassword(),
                    user.getPassword()
                )
            ) {
                throw new RuntimeException("Invalid credentials");
            }

            return jwtService.generateToken(user.getUsername());
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
