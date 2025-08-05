package com.univault.pyqpk.Repo;

import com.univault.pyqpk.Entity.KillInterviewEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KillInterviewRepo
    extends JpaRepository<KillInterviewEntity, UUID> {}
