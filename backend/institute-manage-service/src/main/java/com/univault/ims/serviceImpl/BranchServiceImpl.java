package com.univault.ims.serviceImpl;

import com.univault.ims.dto.BranchDTO;
import com.univault.ims.dto.Mapper.BranchMapper;
import com.univault.ims.entity.Branch;
import com.univault.ims.entity.Course;
import com.univault.ims.exception.service.BranchServiceException;
import com.univault.ims.repository.BranchRepository;
import com.univault.ims.repository.CourseRepository;
import com.univault.ims.service.BranchService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchService {

    private static final Logger logger = LoggerFactory.getLogger(BranchServiceImpl.class);

    private final BranchRepository branchRepo;
    private final CourseRepository courseRepo;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepo, CourseRepository courseRepo) {
        this.branchRepo = branchRepo;
        this.courseRepo = courseRepo;
    }

    @Override
    public BranchDTO getBranchById(UUID id) {
        return branchRepo.findById(id)
                .map(BranchMapper::toDTO)
                .orElseThrow(() -> {
                    String message = "Branch not found with ID: " + id;
                    logger.warn(message);
                    return new BranchServiceException(message);
                });
    }

    @Override
    public List<BranchDTO> getBranchesByCourseId(UUID courseId) {
        List<Branch> branches = branchRepo.findAllBranchesByCourseId(courseId);
        if (branches.isEmpty()) {
            String message = "No Branches found for Course ID: " + courseId;
            logger.warn(message);
            throw new BranchServiceException(message);
        }
        return branches.stream()
                .map(BranchMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BranchDTO createBranch(BranchDTO branchDTO) {
        branchRepo.findByNameAndCourseId(branchDTO.getName(), branchDTO.getCourseId())
                .ifPresent(existing -> {
                    String message = "Branch already exists with name: " + branchDTO.getName();
                    logger.warn(message);
                    throw new BranchServiceException(message);
                });

        Course course = courseRepo.findById(branchDTO.getCourseId())
                .orElseThrow(() -> {
                    String message = "Course not found with ID: " + branchDTO.getCourseId();
                    logger.warn(message);
                    return new BranchServiceException(message);
                });

        try {
            Branch branchEntity = BranchMapper.toEntity(branchDTO, course);
            Branch savedBranch = branchRepo.save(branchEntity);
            logger.info("Branch created successfully with ID: {}", savedBranch.getId());
            return BranchMapper.toDTO(savedBranch);
        } catch (Exception e) {
            logger.error("Error in createBranch", e);
            throw new BranchServiceException("An error occurred while creating the branch.", e);
        }
    }

    @Override
    @Transactional
    public void deleteBranch(UUID branchId) {
        Branch branch = branchRepo.findById(branchId)
                .orElseThrow(() -> {
                    String message = "Branch not found with ID: " + branchId;
                    logger.warn(message);
                    return new BranchServiceException(message);
                });

        try {
            branchRepo.delete(branch);
            logger.info("Branch deleted successfully with ID: {}", branchId);
        } catch (Exception e) {
            logger.error("Error in deleteBranch", e);
            throw new BranchServiceException("An error occurred while deleting the branch.", e);
        }
    }

    @Override
    @Transactional
    public BranchDTO updateBranch(UUID branchId, BranchDTO updatedBranchData) {
        Branch existingBranch = branchRepo.findById(branchId)
                .orElseThrow(() -> {
                    String message = "Branch with ID " + branchId + " not found.";
                    logger.warn(message);
                    return new BranchServiceException(message);
                });

        existingBranch.setName(updatedBranchData.getName());
        existingBranch.setShortname(updatedBranchData.getShortname());
        existingBranch.setCode(updatedBranchData.getCode());
        existingBranch.setDescription(updatedBranchData.getDescription());

        try {
            Branch updatedBranch = branchRepo.save(existingBranch);
            logger.info("Branch updated successfully with ID: {}", branchId);
            return BranchMapper.toDTO(updatedBranch);
        } catch (Exception e) {
            logger.error("Error in updateBranch", e);
            throw new BranchServiceException("An error occurred while updating the branch.", e);
        }
    }
}
