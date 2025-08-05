package com.univault.ims.controller;

import com.univault.ims.dto.UnitDTO;
import com.univault.ims.service.UnitService;
import com.univault.ims.serviceImpl.UnitServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/units")
@CrossOrigin(origins = "*")
public class UnitController {

    private final UnitService unitService;

    @Autowired
    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping("/{subjectId}")
    public ResponseEntity<List<UnitDTO>> getAllUnitsBySubjectId(@PathVariable UUID subjectId) {
        List<UnitDTO> units = unitService.getAllUnitsBySubjectId(subjectId);
        return new ResponseEntity<>(units, HttpStatus.OK);
    }

    // Get unit by unitId
    @GetMapping("/fetchbyid/{unitId}")
    public ResponseEntity<UnitDTO> getUnitById(@PathVariable UUID unitId) {
        UnitDTO unit = unitService.getUnitById(unitId);
        return new ResponseEntity<>(unit, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UnitDTO> createUnitController(@RequestBody @Valid UnitDTO dto) {
        UnitDTO createdUnit = unitService.createUnit(dto);
        return new ResponseEntity<>(createdUnit, HttpStatus.CREATED);
    }

    @DeleteMapping("/{unitId}")
    public ResponseEntity<String> deleteUnitController(@PathVariable UUID unitId) {
        unitService.deleteUnit(unitId);
        return ResponseEntity.status(HttpStatus.OK).body("Unit deleted successfully");
    }

    @PutMapping("/{unitId}")
    public ResponseEntity<UnitDTO> updateUnitController(@PathVariable UUID unitId, @RequestBody @Valid UnitDTO toBeUpdatedUnitData) {
        UnitDTO updatedUnit = unitService.updateUnit(unitId, toBeUpdatedUnitData);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUnit);
    }
}
