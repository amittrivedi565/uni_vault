package com.service.src.Controller;

import com.service.src.Dto.Response.branchResponseDto;
import com.service.src.Entity.branchEntity;
import com.service.src.Service.branchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/branch")
public class branchController {

    @Autowired
    private branchService service;

    @GetMapping
    public ResponseEntity<List<branchResponseDto>> getAllBranchesController() {
        List<branchResponseDto> fetchAllBranches = service.getAllBranchesService();
        return new ResponseEntity<>(fetchAllBranches, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<branchResponseDto> createBranchController(@RequestBody branchEntity branch) {
        branchResponseDto createdBranch = service.postBranchService(branch);
        return new ResponseEntity<>(createdBranch, HttpStatus.CREATED);
    }

    @DeleteMapping("/{branchId}")
    public ResponseEntity<String> deleteBranchController(@PathVariable UUID branchId) {
        service.deleteBranchService(branchId);
        return ResponseEntity.status(HttpStatus.OK).body("Branch deleted successfully");
    }

    @PutMapping("/{branchId}")
    public ResponseEntity<branchResponseDto> updateBranchController(@PathVariable UUID branchId, @RequestBody branchEntity toBeUpdatedBranchData) {
        branchResponseDto updatedBranch = service.updateBranchService(branchId,toBeUpdatedBranchData);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBranch);
    }
}
