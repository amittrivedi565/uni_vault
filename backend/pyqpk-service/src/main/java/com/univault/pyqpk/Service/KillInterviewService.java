package com.univault.pyqpk.Service;

import com.univault.pyqpk.Entity.KillInterviewEntity;
import com.univault.pyqpk.Exception.KillInterviewException;
import com.univault.pyqpk.Repo.KillInterviewRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KillInterviewService {

    @Autowired
    private final KillInterviewRepo killInterviewRepo;

    public KillInterviewService(KillInterviewRepo killInterviewRepo) {
        this.killInterviewRepo = killInterviewRepo;
    }

    public List<KillInterviewEntity> getAllKillInterviews() {
        try {
            return killInterviewRepo.findAll();
        } catch (Exception e) {
            throw new KillInterviewException("Error fetching entities");
        }
    }

    public KillInterviewEntity createKillInterview(KillInterviewEntity entity) {
        try {
            KillInterviewEntity save = killInterviewRepo.save(entity);
            return save;
        } catch (Exception e) {
            throw new KillInterviewException(e.getMessage());
        }
    }

    public String updateKillInterview(KillInterviewEntity entity) {
        try {
            KillInterviewEntity findById = killInterviewRepo
                .findById(entity.getId())
                .orElseThrow(() ->
                    new KillInterviewException("Entity not found")
                );

            findById.setName(entity.getName());
            findById.setLink(entity.getLink());

            return "Entity updated successfully";
        } catch (Exception e) {
            throw new KillInterviewException("Error updating entity");
        }
    }

    public boolean deleteKillInterview(KillInterviewEntity entity) {
        try {
            killInterviewRepo
                .findById(entity.getId())
                .orElseThrow(() ->
                    new KillInterviewException("Entity not found ")
                );

            killInterviewRepo.deleteById(entity.getId());
            return true;
        } catch (Exception e) {
            throw new KillInterviewException("Error deleting entity");
        }
    }
}
