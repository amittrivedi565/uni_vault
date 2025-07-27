package com.univault.ucs.Repository;

import com.univault.ucs.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubjectRepository extends JpaRepository<Subject, UUID> {
    Optional<Subject> findByNameAndSemesterId(String name, UUID id);
    List<Subject> findAllSubjectsBySemesterId(UUID semesterId);
}
