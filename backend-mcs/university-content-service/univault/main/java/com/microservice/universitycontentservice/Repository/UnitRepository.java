package com.microservice.universitycontentservice.Repository;

import com.microservice.universitycontentservice.Entity.Semester;
import com.microservice.universitycontentservice.Entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UnitRepository extends JpaRepository<Unit, UUID> {
    Optional<Unit> findByName(String name);
    Optional<Unit> findByNameAndSubjectId(String name, UUID id);
}
