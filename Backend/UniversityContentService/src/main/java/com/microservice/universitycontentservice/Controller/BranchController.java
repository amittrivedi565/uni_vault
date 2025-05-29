package com.microservice.universitycontentservice.Controller;

import com.microservice.universitycontentservice.Dto.BranchDTO;
import com.microservice.universitycontentservice.Entity.Branch;
import com.microservice.universitycontentservice.Service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/branch")
public class BranchController {

    @Autowired
    private BranchService service;

    @GetMapping
    public ResponseEntity<List<BranchDTO>> getAllBranchesController() {
        List<BranchDTO> fetchAllBranches = service.getAllBranchesService();
        return new ResponseEntity<>(fetchAllBranches, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BranchDTO> createBranchController(@RequestBody Branch branch) {
        BranchDTO createdBranch = service.postBranchService(branch);
        return new ResponseEntity<>(createdBranch, HttpStatus.CREATED);
    }

    @DeleteMapping("/{branchId}")
    public ResponseEntity<String> deleteBranchController(@PathVariable UUID branchId) {
        service.deleteBranchService(branchId);
        return ResponseEntity.status(HttpStatus.OK).body("Branch deleted successfully");
    }

    @PutMapping("/{branchId}")
    public ResponseEntity<BranchDTO> updateBranchController(@PathVariable UUID branchId, @RequestBody Branch toBeUpdatedBranchData) {
        BranchDTO updatedBranch = service.updateBranchService(branchId,toBeUpdatedBranchData);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBranch);
    }
}
