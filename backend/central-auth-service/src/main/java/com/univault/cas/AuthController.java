package com.univault.cas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserType request) {
        authService.register(request);
        return ResponseEntity.ok().body("User registered complete.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserType request) {
        String token = authService.authenticate(request);

        return ResponseEntity.ok(token);
    }
}
