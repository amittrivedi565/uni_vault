package com.univault.ims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class InstituteManageService {
    public static void main(String[] args) {
        SpringApplication.run(InstituteManageService.class, args);
    }
}