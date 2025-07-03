package com.Controller;

import com.DTO.SubjectDTO;
import com.Entity.Subject;
import com.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getAllSubjectsController() {
        List<SubjectDTO> allSubjects = subjectService.getAllSubjects();
        return new ResponseEntity<>(allSubjects, HttpStatus.OK);
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
