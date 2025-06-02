package com.microservice.universitycontentservice.Controller;

import com.microservice.universitycontentservice.DTO.YearDTO;
import com.microservice.universitycontentservice.Entity.Year;
import com.microservice.universitycontentservice.Service.YearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/year")
public class YearController {

    private final YearService yearService;

    @Autowired
    public YearController(YearService yearService) {
        this.yearService = yearService;
    }

    @GetMapping
    public ResponseEntity<List<YearDTO>> getAllYearsController() {
        List<YearDTO> allYears = yearService.getAllYears();
        return new ResponseEntity<>(allYears, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<YearDTO> createYearController(@RequestBody YearDTO dto) {
        YearDTO createdYear = yearService.createYear(dto);
        return new ResponseEntity<>(createdYear, HttpStatus.CREATED);
    }

    @DeleteMapping("/{yearId}")
    public ResponseEntity<String> deleteYearController(@PathVariable UUID yearId) {
        yearService.deleteYear(yearId);
        return ResponseEntity.status(HttpStatus.OK).body("Year deleted successfully");
    }

    @PutMapping("/{yearId}")
    public ResponseEntity<YearDTO> updateYearController(@PathVariable UUID yearId, @RequestBody Year toBeUpdatedYearData) {
        YearDTO updatedYear = yearService.updateYear(yearId, toBeUpdatedYearData);
        return ResponseEntity.status(HttpStatus.OK).body(updatedYear);
    }
}
