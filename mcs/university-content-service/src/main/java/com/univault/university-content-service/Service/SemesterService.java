package com.Service;

import com.DTO.SemesterDTO;
import com.DTO.Mapper.SemesterMapper;
import com.Entity.Semester;
import com.Exceptions.Semester.SemesterServiceException;
import com.Repository.BranchRepository;
import com.Repository.SemesterRepository;
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

    public List<SemesterDTO> getAllSemesters() {
        try {
            List<Semester> semesters = semesterRepo.findAll();
            return semesters.stream()
                    .map(SemesterMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (SemesterServiceException ex) {
            throw ex;
        } catch (Exception e) {
            logger.error("Error in getAllSemesters: {}", e.getMessage());
            throw new SemesterServiceException("An error occurred while fetching all semesters. Please try again later.");
        }
    }

    public List<SemesterDTO> getSemestersByBranch(UUID branchId) {
        try {
            List<Semester> semesters = semesterRepo.findByBranchId(branchId);
            if (semesters.isEmpty()) {
                throw new SemesterServiceException("No semesters found for the given branch.");
            }
            // Convert to DTOs and return
            return semesters.stream()
                    .map(SemesterMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (SemesterServiceException ex) {
            throw ex; // rethrow custom exception
        } catch (Exception e) {
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

        }catch (SemesterServiceException ex) {
            throw ex;
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

        } catch (SemesterServiceException ex) {
            throw ex;
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
            existingSemester.setSyllabus(updatedSemesterData.getSyllabus());

            Semester updatedSemester = semesterRepo.save(existingSemester);
            return SemesterMapper.toDTO(updatedSemester);

        } catch (SemesterServiceException ex) {
            throw ex;
        } catch (Exception e) {
            logger.error("Error in updateSemester: {}", e.getMessage());
            throw new SemesterServiceException("An error occurred while updating the semester. Please try again later.");
        }
    }
}
