package com.microservice.universitycontentservice.Repository;

import com.microservice.universitycontentservice.Entity.Semester;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SemesterRepository extends JpaRepository<Semester, UUID> {
    Optional<Semester> findByName(@NotBlank(message = "Semester name is required") @Size(min = 3, max = 100, message = "Semester name must be between 3 and 100 characters") String name);
}
