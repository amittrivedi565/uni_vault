package com.univault.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="auth")
public class JwtProperties {
    private String secretKey;
    private long expirationTime;
    private String issue;

    public String getSecretKey() {
        return secretKey;
    }
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
    public long getExpirationTime() {
        return expirationTime;
    }
    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }
    public String getIssuer() {
        return issue;
    }
    public void setIssuer(String issue) {
        this.issue = issue;
    }
}
