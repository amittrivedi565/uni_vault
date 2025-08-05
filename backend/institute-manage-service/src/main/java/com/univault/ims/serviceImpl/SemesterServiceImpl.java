package com.univault.ims.serviceImpl;

import com.univault.ims.dto.SemesterDTO;
import com.univault.ims.dto.Mapper.SemesterMapper;
import com.univault.ims.entity.Semester;
import com.univault.ims.exception.service.SemesterServiceException;
import com.univault.ims.repository.BranchRepository;
import com.univault.ims.repository.SemesterRepository;
import com.univault.ims.service.SemesterService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SemesterServiceImpl implements SemesterService {

    private static final Logger logger = LoggerFactory.getLogger(SemesterServiceImpl.class);

    private final SemesterRepository semesterRepo;
    private final BranchRepository branchRepo;

    public SemesterServiceImpl(SemesterRepository semesterRepo, BranchRepository branchRepo) {
        this.semesterRepo = semesterRepo;
        this.branchRepo = branchRepo;
    }

    @Override
    public SemesterDTO getSemesterById(UUID id) {
        return semesterRepo.findById(id)
                .map(SemesterMapper::toDTO)
                .orElseThrow(() -> {
                    String message = "Semester not found with ID: " + id;
                    logger.warn(message);
                    return new SemesterServiceException(message);
                });
    }

    @Override
    public List<SemesterDTO> getSemestersByBranchId(UUID branchId) {
        try {
            List<Semester> semesters = semesterRepo.findAllSemestersByBranchId(branchId);
            if (semesters.isEmpty()) {
                String message = "No semesters found for Branch ID: " + branchId;
                logger.warn(message);
                throw new SemesterServiceException(message);
            }
            return semesters.stream()
                    .map(SemesterMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in getSemestersByBranchId", e);
            throw new SemesterServiceException("An error occurred while fetching semesters by branch id.", e);
        }
    }

    @Override
    public SemesterDTO createSemester(SemesterDTO semesterDTO) {
        semesterRepo.findByNameAndBranchId(semesterDTO.getName(), semesterDTO.getBranchId())
                .ifPresent(existing -> {
                    String message = "Semester already exists with name: " + semesterDTO.getName();
                    logger.warn(message);
                    throw new SemesterServiceException(message);
                });

        branchRepo.findById(semesterDTO.getBranchId())
                .orElseThrow(() -> {
                    String message = "Branch not found with ID: " + semesterDTO.getBranchId();
                    logger.warn(message);
                    return new SemesterServiceException(message);
                });

        try {
            Semester semesterEntity = SemesterMapper.toEntity(semesterDTO);
            Semester savedSemester = semesterRepo.save(semesterEntity);
            logger.info("Semester created successfully with ID: {}", savedSemester.getId());
            return SemesterMapper.toDTO(savedSemester);
        } catch (Exception e) {
            logger.error("Error in createSemester", e);
            throw new SemesterServiceException("An error occurred while creating the semester.", e);
        }
    }

    @Override
    @Transactional
    public void deleteSemester(UUID semesterId) {
        Semester semester = semesterRepo.findById(semesterId)
                .orElseThrow(() -> {
                    String message = "Semester not found with ID: " + semesterId;
                    logger.warn(message);
                    return new SemesterServiceException(message);
                });

        try {
            semesterRepo.delete(semester);
            logger.info("Semester deleted successfully with ID: {}", semesterId);
        } catch (Exception e) {
            logger.error("Error in deleteSemester", e);
            throw new SemesterServiceException("An error occurred while deleting the semester.", e);
        }
    }

    @Override
    @Transactional
    public SemesterDTO updateSemester(UUID semesterId, SemesterDTO updatedSemesterData) {
        Semester existingSemester = semesterRepo.findById(semesterId)
                .orElseThrow(() -> {
                    String message = "Semester not found with ID: " + semesterId;
                    logger.warn(message);
                    return new SemesterServiceException(message);
                });

        existingSemester.setName(updatedSemesterData.getName());
        existingSemester.setCode(updatedSemesterData.getCode());
        existingSemester.setResourceId(updatedSemesterData.getResource_id());

        try {
            Semester updatedSemester = semesterRepo.save(existingSemester);
            logger.info("Semester updated successfully with ID: {}", semesterId);
            return SemesterMapper.toDTO(updatedSemester);
        } catch (Exception e) {
            logger.error("Error in updateSemester", e);
            throw new SemesterServiceException("An error occurred while updating the semester.", e);
        }
    }
}
