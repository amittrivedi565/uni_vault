package com.microservice.universitycontentservice.Repository;

import com.microservice.universitycontentservice.Entity.Course;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    Optional<Course> findByName(String name);
    Optional<Course> findByNameAndInstituteId(String name, UUID id);
}
