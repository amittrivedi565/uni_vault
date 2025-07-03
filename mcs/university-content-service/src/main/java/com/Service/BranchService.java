package com.Service;

import com.DTO.BranchDTO;
import com.DTO.Mapper.BranchMapper;
import com.Entity.Branch;
import com.Entity.Course;
import com.Exceptions.Branch.BranchServiceException;
import com.Exceptions.Semester.SemesterServiceException;
import com.Repository.BranchRepository;
import com.Repository.CourseRepository;
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

    public List<BranchDTO> getAllBranches() {
        try {
            List<Branch> branches = branchRepo.findAll();
            return branches.stream()
                    .map(BranchMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (BranchServiceException ex) {
            throw ex;
        } catch (Exception e) {
            logger.error("Error in getAllBranches: {}", e.getMessage());
            throw new BranchServiceException("An error occurred while fetching all branches. Please try again later.");
        }
    }

    public List<BranchDTO> getSemestersByBranchId(UUID id) {
        try {
            Optional<Branch> branches = branchRepo.findById(id);
            if (branches.isEmpty()) {
                throw new BranchServiceException("No Branches found for the given id.");
            }
            // Convert to DTOs and return
            return branches.stream()
                    .map(BranchMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (SemesterServiceException ex) {
            throw ex;
        } catch (Exception e) {
            throw new SemesterServiceException("An error occurred while fetching the semesters.");
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

        } catch (BranchServiceException ex) {
            throw ex;
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
