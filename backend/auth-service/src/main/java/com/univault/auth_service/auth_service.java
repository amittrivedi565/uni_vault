package com.univault.auth_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class auth_service {

    @Autowired
    private user_repo userRepo;
    private final BCryptPasswordEncoder password_encoder = new BCryptPasswordEncoder();

    public user_entity register(user_type request){
        try{
            user_entity user = new user_entity();
            user.setUsername(request.getUsername());
            user.setPassword(password_encoder.encode(request.getPassword()));
            return userRepo.save(user);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<user_entity> authenticate(user_type request){
        try{
            return userRepo.findByUsername(request.getUsername())
                    .filter(u -> password_encoder.matches(request.getPassword(),u.getPassword()));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }


}
