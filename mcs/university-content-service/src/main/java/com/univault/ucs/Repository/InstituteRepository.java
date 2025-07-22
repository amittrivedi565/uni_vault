package com.Repository;

import com.Entity.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InstituteRepository extends JpaRepository<Institute, UUID> {
    Optional<Institute> findByName(String name);
}