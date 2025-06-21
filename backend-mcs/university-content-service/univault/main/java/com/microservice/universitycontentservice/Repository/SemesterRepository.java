package com.microservice.universitycontentservice.Repository;

import com.microservice.universitycontentservice.Entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SemesterRepository extends JpaRepository<Semester, UUID> {
    Optional<Semester> findByName(String name);
    Optional<Semester> findByNameAndBranchId(String name, UUID id);
}
