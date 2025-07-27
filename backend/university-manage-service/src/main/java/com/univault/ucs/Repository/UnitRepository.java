package com.univault.ucs.Repository;

import com.univault.ucs.Entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UnitRepository extends JpaRepository<Unit, UUID> {
    Optional<Unit> findByNameAndSubjectId(String name, UUID id);
    List<Unit> findAllUnitsBySubjectId(UUID subjectId);
}
