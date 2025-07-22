package com.univault.auth_service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface user_repo extends JpaRepository<user_entity, UUID> {
    Optional<user_entity> findByUsername(String name);
}
