package com.univault_ucs.Repository;

import com.univault_ucs.Entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BranchRepository extends JpaRepository<Branch, UUID> {
    Optional<Branch> findByNameAndCourseId(String name, UUID id);
    List<Branch> findAllBranchesByCourseId(UUID id);
}
