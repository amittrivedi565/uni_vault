package com.microservice.universitycontentservice.Controller;

import com.microservice.universitycontentservice.Dto.Response.instituteResponseDto;
import com.microservice.universitycontentservice.Entity.instituteEntity;
import com.microservice.universitycontentservice.Service.instituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/institute")
public class instituteController {

    @Autowired
    private instituteService service;

    @GetMapping
    public ResponseEntity<List<instituteResponseDto>> getAllInstitutesController() {
        List<instituteResponseDto> fetchAllInstitutes = service.getAllInstitutesService();
        return new ResponseEntity<>(fetchAllInstitutes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<instituteResponseDto> createInstituteController(@RequestBody instituteEntity institute) {
        instituteResponseDto createdInstitute = service.postInstituteService(institute);
        return new ResponseEntity<>(createdInstitute, HttpStatus.CREATED);
    }

    @DeleteMapping("/{instituteId}")
    public ResponseEntity<String> deleteInstitute(@PathVariable UUID instituteId) {
        service.deleteInstituteService(instituteId);
        return ResponseEntity.status(HttpStatus.OK).body("Institute deleted successfully");
    }

    @PutMapping("/{instituteId}")
    public ResponseEntity<instituteResponseDto> updateInstituteController(@PathVariable UUID instituteId, @RequestBody instituteEntity updatedInstituteData) {
        instituteResponseDto updatedInstitute = service.updateInstituteService(instituteId, updatedInstituteData);
        return new ResponseEntity<>(updatedInstitute, HttpStatus.OK);
    }
}
