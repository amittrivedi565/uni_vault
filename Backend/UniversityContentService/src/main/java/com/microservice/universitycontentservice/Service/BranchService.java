package com.microservice.universitycontentservice.Service;

import com.microservice.universitycontentservice.Dto.Mapper.BranchMapper;
import com.microservice.universitycontentservice.Dto.BranchDTO;
import com.microservice.universitycontentservice.Entity.Branch;
import com.microservice.universitycontentservice.Exceptions.Branch.BranchAlreadyExistsException;
import com.microservice.universitycontentservice.Exceptions.Branch.BranchNotFoundException;
import com.microservice.universitycontentservice.Exceptions.Branch.BranchServiceException;
import com.microservice.universitycontentservice.Repository.BranchRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BranchService {

    private final BranchRepository branchRepo;
    private static final Logger logger = LoggerFactory.getLogger(BranchService.class);

    public BranchService(BranchRepository branchRepo) {
        this.branchRepo = branchRepo;
    }

    public List<BranchDTO> getAllBranchesService() {
        try {
            List<Branch> fetchedBranches = branchRepo.findAll();
            if (fetchedBranches.isEmpty()) {
                logger.info("No branches found in the database.");
                return List.of();
            }

            return fetchedBranches.stream()
                    .map(BranchMapper::toDto)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            logger.error("Error fetching branches: {}", e.getMessage(), e);
            throw new BranchServiceException("An error occurred while fetching all branches. Please try again later.");
        }
    }

    public BranchDTO postBranchService(Branch branch) {
        try {
            Optional<Branch> checkForBranchWithName = branchRepo.findByName(branch.getName());
            if (checkForBranchWithName.isPresent()) {
                throw new BranchAlreadyExistsException("Branch " + branch.getName() + " already exists.");
            }

            Branch savedBranch = branchRepo.save(branch);
            return BranchMapper.toDto(savedBranch);

        } catch (DataAccessException e) {
            logger.error("DataAccessException in postBranchService: {}", e.getMessage(), e);
            throw new BranchServiceException("An error has occurred while accessing the database.");
        } catch (BranchAlreadyExistsException e) {
            logger.warn("Branch already exists: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error in postBranchService: {}", e.getMessage(), e);
            throw new BranchServiceException("An error occurred while creating the branch. Please try again later.");
        }
    }

    @Transactional
    public void deleteBranchService(UUID branchId) {
        try {
            branchRepo.findById(branchId)
                    .orElseThrow(() -> new BranchNotFoundException("Branch with ID " + branchId + " not found."));
            branchRepo.deleteById(branchId);
        } catch (DataAccessException e) {
            logger.error("DataAccessException in deleteBranchService: {}", e.getMessage(), e);
            throw new BranchServiceException("An error has occurred while accessing the database.");
        } catch (BranchNotFoundException e) {
            logger.warn("Branch with ID {} not found: {}", branchId, e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error in deleteBranchService: {}", e.getMessage(), e);
            throw new BranchServiceException("An error occurred while deleting the branch. Please try again later.");
        }
    }

    @Transactional
    public BranchDTO updateBranchService(UUID branchId, Branch updatedBranchData) {
        try {
            Branch existingBranch = branchRepo.findById(branchId)
                    .orElseThrow(() -> new BranchNotFoundException("Branch with ID " + branchId + " not found."));

            existingBranch.setName(updatedBranchData.getName());
            existingBranch.setShortname(updatedBranchData.getShortname());
            existingBranch.setCode(updatedBranchData.getCode());
            existingBranch.setDescription(updatedBranchData.getDescription());

            Branch updatedBranch = branchRepo.save(existingBranch);
            return BranchMapper.toDto(updatedBranch);

        } catch (DataAccessException e) {
            logger.error("DataAccessException in updateBranchService: {}", e.getMessage(), e);
            throw new BranchServiceException("An error has occurred while accessing the database.");
        } catch (BranchNotFoundException e) {
            logger.warn("Branch with ID {} not found: {}", branchId, e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error in updateBranchService: {}", e.getMessage(), e);
            throw new BranchServiceException("An error occurred while updating the branch. Please try again later.");
        }
    }

}
