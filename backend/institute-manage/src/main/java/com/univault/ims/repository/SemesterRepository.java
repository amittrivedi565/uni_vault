package com.univault.ims.repository;

import com.univault.ims.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SemesterRepository extends JpaRepository<Semester, UUID> {
    Optional<Semester> findByNameAndBranchId(String name, UUID branchId);
    List<Semester> findAllSemestersByBranchId(UUID branchId);
}
