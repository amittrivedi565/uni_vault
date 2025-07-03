package com.Controller;

import com.DTO.SemesterDTO;
import com.Entity.Semester;
import com.Service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/semester")
public class SemesterController {

    private final SemesterService semesterService;

    @Autowired
    public SemesterController(SemesterService semesterService) {
        this.semesterService = semesterService;
    }

    @GetMapping
    public ResponseEntity<List<SemesterDTO>> getAllSemesters() {
        List<SemesterDTO> allSemesters = semesterService.getAllSemesters();
        return new ResponseEntity<>(allSemesters, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SemesterDTO> createSemester(@RequestBody SemesterDTO dto) {
        SemesterDTO createdSemester = semesterService.createSemester(dto);
        return new ResponseEntity<>(createdSemester, HttpStatus.CREATED);
    }

    @DeleteMapping("/{semesterId}")
    public ResponseEntity<String> deleteSemester(@PathVariable UUID semesterId) {
        semesterService.deleteSemester(semesterId);
        return ResponseEntity.status(HttpStatus.OK).body("Semester deleted successfully");
    }

    @PutMapping("/{semesterId}")
    public ResponseEntity<SemesterDTO> updateSemester(@PathVariable UUID semesterId, @RequestBody Semester toBeUpdatedSemesterData) {
        SemesterDTO updatedSemester = semesterService.updateSemester(semesterId, toBeUpdatedSemesterData);
        return ResponseEntity.status(HttpStatus.OK).body(updatedSemester);
    }
}
