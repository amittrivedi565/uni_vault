package com.univault.auth_service;

public class user_type {
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
