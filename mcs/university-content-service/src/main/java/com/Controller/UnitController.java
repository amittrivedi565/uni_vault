package com.Controller;

import com.DTO.UnitDTO;
import com.Entity.Unit;
import com.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/unit")
public class UnitController {

    private final UnitService unitService;

    @Autowired
    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping
    public ResponseEntity<List<UnitDTO>> getAllUnitsController() {
        List<UnitDTO> allUnits = unitService.getAllUnits();
        return new ResponseEntity<>(allUnits, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UnitDTO> createUnitController(@RequestBody UnitDTO dto) {
        UnitDTO createdUnit = unitService.createUnit(dto);
        return new ResponseEntity<>(createdUnit, HttpStatus.CREATED);
    }

    @DeleteMapping("/{unitId}")
    public ResponseEntity<String> deleteUnitController(@PathVariable UUID unitId) {
        unitService.deleteUnit(unitId);
        return ResponseEntity.status(HttpStatus.OK).body("Unit deleted successfully");
    }

    @PutMapping("/{unitId}")
    public ResponseEntity<UnitDTO> updateUnitController(@PathVariable UUID unitId, @RequestBody Unit toBeUpdatedUnitData) {
        UnitDTO updatedUnit = unitService.updateUnit(unitId, toBeUpdatedUnitData);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUnit);
    }
}
