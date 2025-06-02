package com.microservice.universitycontentservice.Repository;

import com.microservice.universitycontentservice.Entity.Subject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SubjectRepository extends JpaRepository<Subject, UUID> {
    Optional<Subject> findByName(@NotBlank(message = "Subject name is required") @Size(min = 3, max = 100, message = "Subject name must be between 3 and 100 characters") String name);
}
