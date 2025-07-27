package com.univault.cas;

public class UserType {

    private String username;
    private String password;
    private String token;

    // Constructors
    public UserType() {}

    public UserType(String token, String username) {
        this.token = token;
        this.username = username;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
