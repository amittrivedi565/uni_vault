package com.Repository;

import com.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SubjectRepository extends JpaRepository<Subject, UUID> {
    Optional<Subject> findByName(String name);
    Optional<Subject> findByNameAndSemesterId(String name, UUID id);
}
