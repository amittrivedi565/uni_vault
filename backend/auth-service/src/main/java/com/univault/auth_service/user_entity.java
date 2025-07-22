package com.univault.auth_service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class user_entity {
    @Id
    @GeneratedValue
    private UUID id;

    private String username;

    private String password;

    public String setUsername(String username){ return this.username = username;}

    public String getUsername(){
        return this.username;
    }

    public String setPassword(String password){
        return this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

}
