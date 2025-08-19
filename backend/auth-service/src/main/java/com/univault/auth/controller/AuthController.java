package com.univault.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.univault.auth.entity.UserEntity;
import com.univault.auth.service.AuthService;
import com.univault.auth.validate.AuthValidation;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthValidation authValidation;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserEntity request) {
        authService.register(request);
        return ResponseEntity.ok().body("User registered complete.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserEntity request) {
        String token = authService.authenticate(request);
        
            return ResponseEntity.ok()
            .header("Authorization", "Bearer " + token)
            .body("Login successful!");
    }


    @PostMapping("/token")
    public ResponseEntity<?> isTokenValid(@RequestHeader("Authorization") String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
        }
         
        String token = authHeader.substring(7);

        boolean isValid = authValidation.validateToken(token);

        if (isValid) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }
    }
}
