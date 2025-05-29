package com.microservice.universitycontentservice.Repository;

import com.microservice.universitycontentservice.Entity.branchEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface branchRepository extends JpaRepository<branchEntity, UUID> {
    Optional<branchEntity> findByName(@NotBlank(message = "Branch name is required") @Size(min=3,max=100) String name);
}
