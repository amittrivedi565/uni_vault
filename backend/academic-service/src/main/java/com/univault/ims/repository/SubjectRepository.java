package com.univault.ims.repository;

import com.univault.ims.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubjectRepository extends JpaRepository<Subject, UUID> {
    Optional<Subject> findByNameAndSemesterId(String name, UUID id);
    List<Subject> findAllSubjectsBySemesterId(UUID semesterId);
}
