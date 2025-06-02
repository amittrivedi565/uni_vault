package com.microservice.universitycontentservice.Repository;

import com.microservice.universitycontentservice.Entity.Year;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface YearRepository extends JpaRepository<Year, UUID> {

    Optional<Year> findByName(String name);
}
