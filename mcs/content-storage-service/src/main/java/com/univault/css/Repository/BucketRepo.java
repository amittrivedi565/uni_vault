package com.univault.css.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BucketRepo extends JpaRepository<BucketEntity, UUID> {
}
