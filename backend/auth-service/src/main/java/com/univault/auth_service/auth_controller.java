package com.univault.auth_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class auth_controller {
    @Autowired auth_service authService;
    @Autowired jwt jwtService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody user_type request){
        authService.register(request);
        return ResponseEntity.ok().body("User registered complete.");
    }

    @PostMapping("/login")
    public String login(@RequestBody user_type request){
        var usr = authService.authenticate(request)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        var token = jwtService.generate_token(usr.getUsername());
        return ("Login successfully: " + token);
    }

}
