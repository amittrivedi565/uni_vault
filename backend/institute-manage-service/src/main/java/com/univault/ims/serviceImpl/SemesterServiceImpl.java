package com.univault.ims.serviceImpl;

import com.univault.ims.dao.BranchDao;
import com.univault.ims.dao.SemesterDao;
import com.univault.ims.dto.SemesterDTO;
import com.univault.ims.dto.Mapper.SemesterMapper;
import com.univault.ims.entity.Semester;
import com.univault.ims.exception.service.SemesterServiceException;
import com.univault.ims.service.SemesterService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SemesterServiceImpl implements SemesterService {

    private static final Logger logger = LoggerFactory.getLogger(SemesterServiceImpl.class);

    private final SemesterDao semesterDao;
    private final BranchDao branchDao;

    public SemesterServiceImpl(SemesterDao semesterDao, BranchDao branchDao) {
        this.semesterDao = semesterDao;
        this.branchDao = branchDao;
    }

    @Override
    public SemesterDTO getSemesterById(UUID id) {
        return semesterDao.findById(id)
                .map(SemesterMapper::toDTO)
                .orElseThrow(() -> {
                    String message = "Semester not found with ID: " + id;
                    logger.warn(message);
                    return new SemesterServiceException(message);
                });
    }

    @Override
    @Transactional
    @Cacheable("branchCache")
    public List<SemesterDTO> getSemestersByBranchId(UUID branchId) {
        try {
            List<Semester> semesters = semesterDao.findAllSemestersByBranchId(branchId);
            if (semesters.isEmpty()) {
                String message = "No semesters found for Branch ID: " + branchId;
                logger.warn(message);
                throw new SemesterServiceException(message);
            }
            return semesters.stream()
                    .map(semester -> SemesterMapper.toDTO(semester,true))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in getSemestersByBranchId", e);
            throw new SemesterServiceException("An error occurred while fetching semesters by branch id.", e);
        }
    }

    @Override
    public SemesterDTO createSemester(SemesterDTO semesterDTO) {
        semesterDao.findByNameAndBranchId(semesterDTO.getName(), semesterDTO.getBranchId())
                .ifPresent(existing -> {
                    String message = "Semester already exists with name: " + semesterDTO.getName();
                    logger.warn(message);
                    throw new SemesterServiceException(message);
                });

        branchDao.findById(semesterDTO.getBranchId())
                .orElseThrow(() -> {
                    String message = "Branch not found with ID: " + semesterDTO.getBranchId();
                    logger.warn(message);
                    return new SemesterServiceException(message);
                });

        try {
            Semester semesterEntity = SemesterMapper.toEntity(semesterDTO);
            Semester savedSemester = semesterDao.save(semesterEntity);
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
        Semester semester = semesterDao.findById(semesterId)
                .orElseThrow(() -> {
                    String message = "Semester not found with ID: " + semesterId;
                    logger.warn(message);
                    return new SemesterServiceException(message);
                });

        try {
            semesterDao.delete(semester);
            logger.info("Semester deleted successfully with ID: {}", semesterId);
        } catch (Exception e) {
            logger.error("Error in deleteSemester", e);
            throw new SemesterServiceException("An error occurred while deleting the semester.", e);
        }
    }

    @Override
    @Transactional
    public SemesterDTO updateSemester(UUID semesterId, SemesterDTO updatedSemesterData) {
        Semester existingSemester = semesterDao.findById(semesterId)
                .orElseThrow(() -> {
                    String message = "Semester not found with ID: " + semesterId;
                    logger.warn(message);
                    return new SemesterServiceException(message);
                });

        existingSemester.setName(updatedSemesterData.getName());
        existingSemester.setCode(updatedSemesterData.getCode());
        existingSemester.setResourceId(updatedSemesterData.getResource_id());

        try {
            Semester updatedSemester = semesterDao.save(existingSemester);
            logger.info("Semester updated successfully with ID: {}", semesterId);
            return SemesterMapper.toDTO(updatedSemester);
        } catch (Exception e) {
            logger.error("Error in updateSemester", e);
            throw new SemesterServiceException("An error occurred while updating the semester.", e);
        }
    }
}
