package com.microservice.universitycontentservice.Repository;

import com.microservice.universitycontentservice.Entity.Unit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UnitRepository extends JpaRepository<Unit, UUID> {
    Optional<Unit> findByName(@NotBlank(message = "Unit name is required") @Size(min = 3, max = 100, message = "Unit name must be between 3 and 100 characters") String name);
}
