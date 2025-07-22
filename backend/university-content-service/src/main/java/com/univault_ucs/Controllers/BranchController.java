package com.univault_ucs.Controllers;

import com.univault_ucs.DTO.BranchDTO;
import com.univault_ucs.Entity.Branch;
import com.univault_ucs.Services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/branches")
@CrossOrigin(origins = "*")
public class BranchController {

    private final BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<List<BranchDTO>> getBranchesByCourseId(@PathVariable UUID courseId) {
        List<BranchDTO> allBranches = branchService.getBranchesByCourseId(courseId);
        return new ResponseEntity<>(allBranches, HttpStatus.OK);
    }

    @GetMapping("/fetchbyid/{branchId}")
    public ResponseEntity<BranchDTO> getBranchByIdController(@PathVariable UUID branchId) {
        BranchDTO branchDTO = branchService.getBranchById(branchId);
        return new ResponseEntity<>(branchDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BranchDTO> createBranchController(@RequestBody BranchDTO dto) {
        BranchDTO createdBranch = branchService.createBranch(dto);
        return new ResponseEntity<>(createdBranch, HttpStatus.CREATED);
    }

    @DeleteMapping("/{branchId}")
    public ResponseEntity<String> deleteBranchController(@PathVariable UUID branchId) {
        branchService.deleteBranch(branchId);
        return ResponseEntity.ok("Branch deleted successfully");
    }

    @PutMapping("/{branchId}")
    public ResponseEntity<BranchDTO> updateBranchController(@PathVariable UUID branchId, @RequestBody Branch updatedBranchData) {
        BranchDTO updatedBranch = branchService.updateBranch(branchId, updatedBranchData);
        return ResponseEntity.ok(updatedBranch);
    }
}
