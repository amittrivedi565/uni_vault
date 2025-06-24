package com.Controller;

import com.DTO.InstituteDTO;
import com.Entity.Institute;
import com.Service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/institute")
public class InstituteController {

    private final InstituteService instituteService;

    @Autowired
    public InstituteController(InstituteService instituteService) {
        this.instituteService = instituteService;
    }

    @GetMapping
    public ResponseEntity<List<InstituteDTO>> getAllInstitutesController() {
        List<InstituteDTO> allInstitutes = instituteService.getAllInstitutes();
        return new ResponseEntity<>(allInstitutes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InstituteDTO> createInstituteController(@RequestBody InstituteDTO dto) {
        InstituteDTO createdInstitute = instituteService.createInstitute(dto);
        return new ResponseEntity<>(createdInstitute, HttpStatus.CREATED);
    }

    @DeleteMapping("/{instituteId}")
    public ResponseEntity<String> deleteInstituteController(@PathVariable UUID instituteId) {
        instituteService.deleteInstitute(instituteId);
        return ResponseEntity.ok("Institute deleted successfully");
    }

    @PutMapping("/{instituteId}")
    public ResponseEntity<InstituteDTO> updateInstituteController(@PathVariable UUID instituteId, @RequestBody Institute updatedInstituteData) {
        InstituteDTO updatedInstitute = instituteService.updateInstitute(instituteId, updatedInstituteData);
        return ResponseEntity.ok(updatedInstitute);
    }
}
