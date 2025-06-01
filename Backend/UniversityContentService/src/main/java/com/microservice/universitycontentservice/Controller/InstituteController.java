package com.microservice.universitycontentservice.Controller;

import com.microservice.universitycontentservice.DTO.InstituteDTO;
import com.microservice.universitycontentservice.Entity.Institute;
import com.microservice.universitycontentservice.Service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/institute")
public class InstituteController {

    private final InstituteService instituteService;

    @Autowired
    InstituteController(InstituteService instituteService){
        this.instituteService = instituteService;
    }

    @GetMapping
    public ResponseEntity<List<InstituteDTO>> getAllInstitutesController() {
        List<InstituteDTO> fetchAllInstitutes = instituteService.getAllInstitutesService();
        return new ResponseEntity<>(fetchAllInstitutes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InstituteDTO> createInstituteController(@RequestBody Institute institute) {
        InstituteDTO createdInstitute = instituteService.postInstituteService(institute);
        return new ResponseEntity<>(createdInstitute, HttpStatus.CREATED);
    }

    @DeleteMapping("/{instituteId}")
    public ResponseEntity<String> deleteInstitute(@PathVariable UUID instituteId) {
        instituteService.deleteInstituteService(instituteId);
        return ResponseEntity.status(HttpStatus.OK).body("Institute deleted successfully");
    }

    @PutMapping("/{instituteId}")
    public ResponseEntity<InstituteDTO> updateInstituteController(@PathVariable UUID instituteId, @RequestBody Institute updatedInstituteData) {
        InstituteDTO updatedInstitute = instituteService.updateInstituteService(instituteId, updatedInstituteData);
        return new ResponseEntity<>(updatedInstitute, HttpStatus.OK);
    }
}
