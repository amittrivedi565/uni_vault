package com.univault_ucs.Repository;

import com.univault_ucs.Entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SemesterRepository extends JpaRepository<Semester, UUID> {
    Optional<Semester> findByNameAndBranchId(String name, UUID id);
    List<Semester> findAllSemestersByBranchId(UUID id);
}
