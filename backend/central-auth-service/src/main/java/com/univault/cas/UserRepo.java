package com.univault.cas;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsername(String name);
}
