package com.univault.ims.controller;

import com.univault.ims.dto.InstituteDTO;
import com.univault.ims.service.InstituteService;
import com.univault.ims.serviceImpl.InstituteServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/institutes")
@CrossOrigin(origins = "*")
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

    @GetMapping("/fetchbyid/{instituteId}")
    public ResponseEntity<InstituteDTO> getInstituteById(@PathVariable UUID instituteId) {
        InstituteDTO institute = instituteService.getInstituteById(instituteId);
        return new ResponseEntity<>(institute, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InstituteDTO> createInstituteController(@RequestBody @Valid InstituteDTO dto) {
        System.out.println("Received DTO: " + dto);
        InstituteDTO createdInstitute = instituteService.createInstitute(dto);
        return new ResponseEntity<>(createdInstitute, HttpStatus.CREATED);
    }

    @DeleteMapping("/{instituteId}")
    public ResponseEntity<String> deleteInstituteController(@PathVariable UUID instituteId) {
        instituteService.deleteInstitute(instituteId);
        return ResponseEntity.ok("Institute deleted successfully");
    }

    @PutMapping("/{instituteId}")
    public ResponseEntity<InstituteDTO> updateInstituteController(@PathVariable UUID instituteId, @RequestBody @Valid InstituteDTO dto) {
        System.out.println("Received update data: " + dto);
        InstituteDTO updatedInstitute = instituteService.updateInstitute(instituteId, dto);
        return ResponseEntity.ok(updatedInstitute);
    }
}
