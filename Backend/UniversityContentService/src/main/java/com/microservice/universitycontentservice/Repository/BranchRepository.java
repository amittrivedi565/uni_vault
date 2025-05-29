package com.microservice.universitycontentservice.Repository;

import com.microservice.universitycontentservice.Entity.Branch;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BranchRepository extends JpaRepository<Branch, UUID> {
    Optional<Branch> findByName(@NotBlank(message = "Branch name is required") @Size(min=3,max=100) String name);
}
