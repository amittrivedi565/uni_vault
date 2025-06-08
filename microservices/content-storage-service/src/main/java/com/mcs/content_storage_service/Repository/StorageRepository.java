package com.mcs.content_storage_service.Repository;

import com.mcs.content_storage_service.Entity.StorageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StorageRepository extends JpaRepository<StorageEntity, UUID> {
    Optional<StorageEntity> findByBucketAndObjectKey(String bucket, String key);
}

