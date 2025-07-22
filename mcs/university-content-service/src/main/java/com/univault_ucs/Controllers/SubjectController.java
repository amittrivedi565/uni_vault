package com.univault_ucs.Controllers;

import com.univault_ucs.DTO.SubjectDTO;
import com.univault_ucs.Entity.Subject;
import com.univault_ucs.Services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/subjects")
@CrossOrigin(origins = "*")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/{semesterId}")
    public ResponseEntity<List<SubjectDTO>> getAllSubjectsBySemesterId(@PathVariable UUID semesterId) {
        List<SubjectDTO> subjects = subjectService.getAllSubjectsBySemesterId(semesterId);
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    // Get subject by subjectId
    @GetMapping("/fetchbyid/{subjectId}")
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable UUID subjectId) {
        SubjectDTO subject = subjectService.getSubjectById(subjectId);
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SubjectDTO> createSubjectController(@RequestBody SubjectDTO dto) {
        SubjectDTO createdSubject = subjectService.createSubject(dto);
        return new ResponseEntity<>(createdSubject, HttpStatus.CREATED);
    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<String> deleteSubjectController(@PathVariable UUID subjectId) {
        subjectService.deleteSubject(subjectId);
        return ResponseEntity.status(HttpStatus.OK).body("Subject deleted successfully");
    }

    @PutMapping("/{subjectId}")
    public ResponseEntity<SubjectDTO> updateSubjectController(@PathVariable UUID subjectId, @RequestBody Subject toBeUpdatedSubjectData) {
        SubjectDTO updatedSubject = subjectService.updateSubject(subjectId, toBeUpdatedSubjectData);
        return ResponseEntity.status(HttpStatus.OK).body(updatedSubject);
    }
}
