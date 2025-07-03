package com.Repository;

import com.Entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SemesterRepository extends JpaRepository<Semester, UUID> {
    Optional<Semester> findByName(String name);
    Optional<Semester> findByNameAndBranchId(String name, UUID id);
    List<Semester> findByBranchId(UUID branchId);
}
