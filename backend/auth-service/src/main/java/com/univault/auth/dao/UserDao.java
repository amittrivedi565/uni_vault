package com.univault.auth.dao;

import com.univault.auth.entity.UserEntity;
import com.univault.auth.repository.UserRepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private UserRepo userRepo;

    public Optional<UserEntity> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public UserEntity save(UserEntity user) {
        return userRepo.save(user);
    }
}
