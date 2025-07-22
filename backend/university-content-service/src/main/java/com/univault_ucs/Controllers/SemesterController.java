package com.univault_ucs.Controllers;

import com.univault_ucs.DTO.SemesterDTO;
import com.univault_ucs.Entity.Semester;
import com.univault_ucs.Services.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/semesters")
@CrossOrigin(origins = "*")
public class SemesterController {

    private final SemesterService semesterService;

    @Autowired
    public SemesterController(SemesterService semesterService) {
        this.semesterService = semesterService;
    }

    @GetMapping("/{branchId}")
    public ResponseEntity<List<SemesterDTO>> getSemestersByBranchId(@PathVariable UUID branchId) {
        List<SemesterDTO> semesters = semesterService.getSemestersByBranchId(branchId);
        return new ResponseEntity<>(semesters, HttpStatus.OK);
    }

    // GET a semester by semesterId
    @GetMapping("/fetchbyid/{semesterId}")
    public ResponseEntity<SemesterDTO> getSemesterByBranchId(@PathVariable UUID semesterId) {
        SemesterDTO semester = semesterService.getSemesterById(semesterId);
        return new ResponseEntity<>(semester, HttpStatus.OK);
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
