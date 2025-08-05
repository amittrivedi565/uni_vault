package com.univault.pyqpk.Controller;

import com.univault.pyqpk.Entity.KillInterviewEntity;
import com.univault.pyqpk.Service.KillInterviewService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kill-interview")
public class KillInterviewController {

    private final KillInterviewService killInterviewService;

    public KillInterviewController(KillInterviewService killInterviewService) {
        this.killInterviewService = killInterviewService;
    }

    @GetMapping
    public ResponseEntity<List<KillInterviewEntity>> getAllEntities() {
        return ResponseEntity.ok(killInterviewService.getAllKillInterviews());
    }

    @PostMapping
    public ResponseEntity<KillInterviewEntity> createEntity(
        @RequestBody KillInterviewEntity entity
    ) {
        return ResponseEntity.ok(
            killInterviewService.createKillInterview(entity)
        );
    }
}
