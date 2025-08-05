package com.univault.ims.serviceImpl;

import com.univault.ims.dto.Mapper.SubjectMapper;
import com.univault.ims.dto.SubjectDTO;
import com.univault.ims.entity.Subject;
import com.univault.ims.exception.service.SubjectServiceException;
import com.univault.ims.repository.SemesterRepository;
import com.univault.ims.repository.SubjectRepository;
import com.univault.ims.service.SubjectService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    private static final Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class);

    private final SubjectRepository subjectRepo;
    private final SemesterRepository semesterRepo;

    public SubjectServiceImpl(SubjectRepository subjectRepo, SemesterRepository semesterRepo) {
        this.subjectRepo = subjectRepo;
        this.semesterRepo = semesterRepo;
    }

    @Override
    public SubjectDTO getSubjectById(UUID id) {
        return subjectRepo.findById(id)
                .map(SubjectMapper::toDTO)
                .orElseThrow(() -> {
                    String message = "Subject not found with ID: " + id;
                    logger.warn(message);
                    return new SubjectServiceException(message);
                });
    }

    @Override
    public List<SubjectDTO> getAllSubjectsBySemesterId(UUID semesterId) {
        try {
            List<Subject> subjects = subjectRepo.findAllSubjectsBySemesterId(semesterId);
            if (subjects.isEmpty()) {
                String message = "No subjects found for Semester ID: " + semesterId;
                logger.warn(message);
                throw new SubjectServiceException(message);
            }
            return subjects.stream()
                    .map(SubjectMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in getAllSubjectsBySemesterId", e);
            throw new SubjectServiceException("An error occurred while fetching subjects by semester id.", e);
        }
    }

    @Override
    public SubjectDTO createSubject(SubjectDTO subjectDTO) {
        subjectRepo.findByNameAndSemesterId(subjectDTO.getName(), subjectDTO.getSemesterId())
                .ifPresent(existing -> {
                    String message = "Subject already exists with name: " + subjectDTO.getName();
                    logger.warn(message);
                    throw new SubjectServiceException(message);
                });

        semesterRepo.findById(subjectDTO.getSemesterId())
                .orElseThrow(() -> {
                    String message = "Semester not found with ID: " + subjectDTO.getSemesterId();
                    logger.warn(message);
                    return new SubjectServiceException(message);
                });

        try {
            Subject subjectEntity = SubjectMapper.toEntity(subjectDTO);
            Subject savedSubject = subjectRepo.save(subjectEntity);
            logger.info("Subject created successfully with ID: {}", savedSubject.getId());
            return SubjectMapper.toDTO(savedSubject);
        } catch (Exception e) {
            logger.error("Error in createSubject", e);
            throw new SubjectServiceException("An error occurred while creating the subject.", e);
        }
    }

    @Override
    @Transactional
    public void deleteSubject(UUID subjectId) {
        Subject subject = subjectRepo.findById(subjectId)
                .orElseThrow(() -> {
                    String message = "Subject not found with ID: " + subjectId;
                    logger.warn(message);
                    return new SubjectServiceException(message);
                });

        try {
            subjectRepo.delete(subject);
            logger.info("Subject deleted successfully with ID: {}", subjectId);
        } catch (Exception e) {
            logger.error("Error in deleteSubject", e);
            throw new SubjectServiceException("An error occurred while deleting the subject.", e);
        }
    }

    @Override
    @Transactional
    public SubjectDTO updateSubject(UUID subjectId, SubjectDTO updatedSubjectData) {
        Subject existingSubject = subjectRepo.findById(subjectId)
                .orElseThrow(() -> {
                    String message = "Subject not found with ID: " + subjectId;
                    logger.warn(message);
                    return new SubjectServiceException(message);
                });

        existingSubject.setName(updatedSubjectData.getName());
        existingSubject.setShortname(updatedSubjectData.getShortname());
        existingSubject.setCode(updatedSubjectData.getCode());
        existingSubject.setDescription(updatedSubjectData.getDescription());

        try {
            Subject updatedSubject = subjectRepo.save(existingSubject);
            logger.info("Subject updated successfully with ID: {}", subjectId);
            return SubjectMapper.toDTO(updatedSubject);
        } catch (Exception e) {
            logger.error("Error in updateSubject", e);
            throw new SubjectServiceException("An error occurred while updating the subject.", e);
        }
    }

    @Override
    public List<SubjectDTO> getSubjects() {
        try {
            List<Subject> subjects = subjectRepo.findAll();
            return subjects.stream()
                    .map(SubjectMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in getSubjects", e);
            throw new SubjectServiceException("An error occurred while fetching all subjects.", e);
        }
    }
}
