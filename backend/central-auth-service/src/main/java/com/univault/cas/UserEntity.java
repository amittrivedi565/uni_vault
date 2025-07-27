package com.univault.cas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.Data;

@Entity
@Data
public class UserEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String username;

    private String password;

    // Setters & Getters
    public String setUsername(String username) {
        return this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public String setPassword(String password) {
        return this.password = password;
    }

    public String getPassword() {
        return this.password;
    }
}
