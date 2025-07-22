package com.univault_ucs.Services;

import com.univault_ucs.DTO.BranchDTO;
import com.univault_ucs.DTO.Mapper.BranchMapper;
import com.univault_ucs.Entity.Branch;
import com.univault_ucs.Entity.Course;
import com.univault_ucs.Exceptions.Branch.BranchServiceException;
import com.univault_ucs.Repository.BranchRepository;
import com.univault_ucs.Repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BranchService {
    private static final Logger logger = LoggerFactory.getLogger(BranchService.class);

    private final BranchRepository branchRepo;
    private final CourseRepository courseRepo;

    @Autowired
    public BranchService(BranchRepository branchRepo, CourseRepository courseRepo) {
        this.branchRepo = branchRepo;
        this.courseRepo = courseRepo;
    }

    public BranchDTO getBranchById(UUID id) {
        try {
            Optional<Branch> find = branchRepo.findById(id);
            if (find.isPresent()) {
                Branch branch = find.get();
                return BranchMapper.toDTO(branch);
            } else {
                throw new BranchServiceException("Branch not found with ID: " + id);
            }
        } catch (Exception e) {
            logger.error("Error in getBranchById: {}", e.getMessage());
            throw new BranchServiceException("An error occurred while fetching branch by id. Please try again later.");
        }
    }


    public List<BranchDTO> getBranchesByCourseId(UUID courseId) {
        try {
            List<Branch> branches = branchRepo.findAllBranchesByCourseId(courseId);
            if (branches.isEmpty()) {
                throw new BranchServiceException("No Branches found for the given course id.");
            }
            return branches.stream()
                    .map(BranchMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in getBranchesByCourseId: {}", e.getMessage());
            throw new BranchServiceException("An error occurred while fetching the branches. Please try again later.");
        }
    }


    public BranchDTO createBranch(BranchDTO branchDTO) {
        try {
            Optional<Branch> existingBranch = branchRepo.findByNameAndCourseId(branchDTO.getName(),branchDTO.getCourseId());
            if (existingBranch.isPresent()) {
                throw new BranchServiceException("Branch already exists with this name.");
            }

            Course course = courseRepo.findById(branchDTO.getCourseId())
                    .orElseThrow(() -> new BranchServiceException("Course not found with this id."));

            Branch branchEntity = BranchMapper.toEntity(branchDTO, course);

            Branch savedBranch = branchRepo.save(branchEntity);

            return BranchMapper.toDTO(savedBranch);

        } catch (Exception e) {
            logger.error("Error in createBranch", e);
            throw new BranchServiceException("An error occurred while creating the branch. Please try again later.");
        }
    }

    @Transactional
    public void deleteBranch(UUID branchId) {
        try {
            branchRepo.findById(branchId)
                    .orElseThrow(() -> new BranchServiceException("Branch with Id " + branchId + " not found."));

            branchRepo.deleteById(branchId);
        } catch (BranchServiceException ex) {
            throw ex;
        } catch (Exception e) {
            logger.error("Error in deleteBranch: {}", e.getMessage());
            throw new BranchServiceException("An error occurred while deleting the branch. Please try again later.");
        }
    }

    @Transactional
    public BranchDTO updateBranch(UUID branchId, Branch updatedBranchData) {
        try {
            Branch existingBranch = branchRepo.findById(branchId)
                    .orElseThrow(() -> new BranchServiceException("Branch with Id " + branchId + " not found."));

            existingBranch.setName(updatedBranchData.getName());
            existingBranch.setCode(updatedBranchData.getCode());
            existingBranch.setDescription(updatedBranchData.getDescription());

            Branch updatedBranch = branchRepo.save(existingBranch);
            return BranchMapper.toDTO(updatedBranch);

        } catch (BranchServiceException ex) {
            throw ex;
        } catch (Exception e) {
            logger.error("Error in updateBranch: {}", e.getMessage());
            throw new BranchServiceException("An error occurred while updating the branch. Please try again later.");
        }
    }
}
