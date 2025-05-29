package com.service.src.Repository;

import com.service.src.Entity.instituteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface instituteRepository extends JpaRepository<instituteEntity, UUID> {
    Optional<instituteEntity> findByName(String name);
}