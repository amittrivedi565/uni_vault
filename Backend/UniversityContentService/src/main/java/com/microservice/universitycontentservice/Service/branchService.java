package com.microservice.universitycontentservice.Service;

import com.microservice.universitycontentservice.Dto.Mapper.branchMapper;
import com.microservice.universitycontentservice.Dto.Response.branchResponseDto;
import com.microservice.universitycontentservice.Entity.branchEntity;
import com.microservice.universitycontentservice.Exceptions.Branch.branchAlreadyExistsException;
import com.microservice.universitycontentservice.Exceptions.Branch.branchNotFoundException;
import com.microservice.universitycontentservice.Exceptions.Branch.branchServiceException;
import com.microservice.universitycontentservice.Repository.branchRepository;
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
public class branchService {

    private final branchRepository branchRepo;
    private static final Logger logger = LoggerFactory.getLogger(branchService.class);

    public branchService(branchRepository branchRepo) {
        this.branchRepo = branchRepo;
    }

    public List<branchResponseDto> getAllBranchesService() {
        try {
            List<branchEntity> fetchedBranches = branchRepo.findAll();
            if (fetchedBranches.isEmpty()) {
                logger.info("No branches found in the database.");
                return List.of();
            }

            return fetchedBranches.stream()
                    .map(branchMapper::toDto)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            logger.error("Error fetching branches: {}", e.getMessage(), e);
            throw new branchServiceException("An error occurred while fetching all branches. Please try again later.");
        }
    }

    public branchResponseDto postBranchService(branchEntity branch) {
        try {
            Optional<branchEntity> checkForBranchWithName = branchRepo.findByName(branch.getName());
            if (checkForBranchWithName.isPresent()) {
                throw new branchAlreadyExistsException("Branch " + branch.getName() + " already exists.");
            }

            branchEntity savedBranch = branchRepo.save(branch);
            return branchMapper.toDto(savedBranch);

        } catch (DataAccessException e) {
            logger.error("DataAccessException in postBranchService: {}", e.getMessage(), e);
            throw new branchServiceException("An error has occurred while accessing the database.");
        } catch (branchAlreadyExistsException e) {
            logger.warn("Branch already exists: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error in postBranchService: {}", e.getMessage(), e);
            throw new branchServiceException("An error occurred while creating the branch. Please try again later.");
        }
    }

    @Transactional
    public void deleteBranchService(UUID branchId) {
        try {
            branchRepo.findById(branchId)
                    .orElseThrow(() -> new branchNotFoundException("Branch with ID " + branchId + " not found."));
            branchRepo.deleteById(branchId);
        } catch (DataAccessException e) {
            logger.error("DataAccessException in deleteBranchService: {}", e.getMessage(), e);
            throw new branchServiceException("An error has occurred while accessing the database.");
        } catch (branchNotFoundException e) {
            logger.warn("Branch with ID {} not found: {}", branchId, e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error in deleteBranchService: {}", e.getMessage(), e);
            throw new branchServiceException("An error occurred while deleting the branch. Please try again later.");
        }
    }

    @Transactional
    public branchResponseDto updateBranchService(UUID branchId, branchEntity updatedBranchData) {
        try {
            branchEntity existingBranch = branchRepo.findById(branchId)
                    .orElseThrow(() -> new branchNotFoundException("Branch with ID " + branchId + " not found."));

            existingBranch.setName(updatedBranchData.getName());
            existingBranch.setShortname(updatedBranchData.getShortname());
            existingBranch.setCode(updatedBranchData.getCode());
            existingBranch.setDescription(updatedBranchData.getDescription());

            branchEntity updatedBranch = branchRepo.save(existingBranch);
            return branchMapper.toDto(updatedBranch);

        } catch (DataAccessException e) {
            logger.error("DataAccessException in updateBranchService: {}", e.getMessage(), e);
            throw new branchServiceException("An error has occurred while accessing the database.");
        } catch (branchNotFoundException e) {
            logger.warn("Branch with ID {} not found: {}", branchId, e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error in updateBranchService: {}", e.getMessage(), e);
            throw new branchServiceException("An error occurred while updating the branch. Please try again later.");
        }
    }

}
