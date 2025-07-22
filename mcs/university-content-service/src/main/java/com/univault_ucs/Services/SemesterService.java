package com.univault_ucs.Services;

import com.univault_ucs.DTO.SemesterDTO;
import com.univault_ucs.DTO.Mapper.SemesterMapper;
import com.univault_ucs.Entity.Semester;
import com.univault_ucs.Exceptions.Semester.SemesterServiceException;
import com.univault_ucs.Repository.BranchRepository;
import com.univault_ucs.Repository.SemesterRepository;
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
public class SemesterService {

    private static final Logger logger = LoggerFactory.getLogger(SemesterService.class);

    private final SemesterRepository semesterRepo;
    private final BranchRepository branchRepo;

    @Autowired
    public SemesterService(SemesterRepository semesterRepo,  BranchRepository branchRepo) {
        this.semesterRepo = semesterRepo;
        this.branchRepo = branchRepo;
    }

    public SemesterDTO getSemesterById(UUID id) {
        try {
            Optional<Semester> find = semesterRepo.findById(id);
            if (find.isPresent()) {
                Semester semester = find.get();
                return SemesterMapper.toDTO(semester);
            } else {
                throw new SemesterServiceException("Semester not found with ID: " + id);
            }
        } catch (Exception e) {
            logger.error("Error in getSemesterById: {}", e.getMessage());
            throw new SemesterServiceException("An error occurred while fetching semester by id. Please try again later.");
        }
    }

    public List<SemesterDTO> getSemestersByBranchId(UUID branchId) {
        try {
            List<Semester> semesters = semesterRepo.findAllSemestersByBranchId(branchId);
            if (semesters.isEmpty()) {
                throw new SemesterServiceException("No semesters found for the given branch id.");
            }
            return semesters.stream()
                    .map(SemesterMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in getSemestersByBranchId: {}", e.getMessage());
            throw new SemesterServiceException("An error occurred while fetching the semesters.");
        }
    }

    public SemesterDTO createSemester(SemesterDTO semesterDTO) {
        try {
            Optional<Semester> existingSemester = semesterRepo.findByNameAndBranchId(semesterDTO.getName(),semesterDTO.getBranchId());
            if (existingSemester.isPresent()) {
                throw new SemesterServiceException("Semester already exists with this name.");
            }

            branchRepo.findById(semesterDTO.getBranchId())
                    .orElseThrow(() -> new SemesterServiceException("Branch not found with this id."));

            Semester semesterEntity = SemesterMapper.toEntity(semesterDTO);

            Semester savedSemester = semesterRepo.save(semesterEntity);

            return SemesterMapper.toDTO(savedSemester);

        } catch (Exception e) {
            logger.error("Error in createSemester", e);
            throw new SemesterServiceException("An error occurred while creating the semester. Please try again later.");
        }
    }

    @Transactional
    public void deleteSemester(UUID semesterId) {
        try {
            semesterRepo.findById(semesterId)
                    .orElseThrow(() -> new SemesterServiceException("Semester with Id " + semesterId + " not found."));

            semesterRepo.deleteById(semesterId);

        } catch (Exception e) {
            logger.error("Error in deleteSemester: {}", e.getMessage());
            throw new SemesterServiceException("An error occurred while deleting the semester. Please try again later.");
        }
    }

    @Transactional
    public SemesterDTO updateSemester(UUID semesterId, Semester updatedSemesterData) {
        try {
            Semester existingSemester = semesterRepo.findById(semesterId)
                    .orElseThrow(() -> new SemesterServiceException("Semester with Id " + semesterId + " not found."));

            existingSemester.setName(updatedSemesterData.getName());
            existingSemester.setCode(updatedSemesterData.getCode());
            existingSemester.setResource_id(updatedSemesterData.getResource_id());

            Semester updatedSemester = semesterRepo.save(existingSemester);
            return SemesterMapper.toDTO(updatedSemester);

        } catch (Exception e) {
            logger.error("Error in updateSemester: {}", e.getMessage());
            throw new SemesterServiceException("An error occurred while updating the semester. Please try again later.");
        }
    }
}
