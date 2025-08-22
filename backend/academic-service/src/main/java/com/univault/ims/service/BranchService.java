package com.univault.ims.service;

import com.univault.ims.dto.BranchDTO;

import java.util.List;
import java.util.UUID;

public interface BranchService {
    BranchDTO getBranchById(UUID id);
    List<BranchDTO> getBranchesByCourseId(UUID courseId);
    BranchDTO createBranch(BranchDTO branchDTO);
    void deleteBranch(UUID branchId);
    BranchDTO updateBranch(UUID branchId, BranchDTO updatedBranchData);
}
