package com.microservice.universitycontentservice.Controller;

import com.microservice.universitycontentservice.DTO.BranchDTO;
import com.microservice.universitycontentservice.Entity.Branch;
import com.microservice.universitycontentservice.Service.BranchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/branch")
public class BranchController {

    private final BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService){
        this.branchService = branchService;
    }

    @GetMapping
    public ResponseEntity<List<BranchDTO>> getAllBranchesController() {
        List<BranchDTO> fetchAllBranches = branchService.getAllBranches();
        return new ResponseEntity<>(fetchAllBranches, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BranchDTO> createBranchController(@Valid @RequestBody BranchDTO dto) {
        BranchDTO createdBranch = branchService.createBranch(dto);
        return new ResponseEntity<>(createdBranch, HttpStatus.CREATED);
    }

    @DeleteMapping("/{branchId}")
    public ResponseEntity<String> deleteBranchController(@PathVariable UUID branchId) {
        branchService.deleteBranch(branchId);
        return ResponseEntity.status(HttpStatus.OK).body("Branch deleted successfully");
    }

    @PutMapping("/{branchId}")
    public ResponseEntity<BranchDTO> updateBranchController (@Valid @PathVariable UUID branchId, @RequestBody Branch toBeUpdatedBranchData) {
        BranchDTO updatedBranch = branchService.updateBranch(branchId,toBeUpdatedBranchData);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBranch);
    }
}
