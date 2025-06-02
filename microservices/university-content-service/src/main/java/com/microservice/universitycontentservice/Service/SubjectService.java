package com.microservice.universitycontentservice.Service;

import com.microservice.universitycontentservice.DTO.Mapper.SubjectMapper;
import com.microservice.universitycontentservice.DTO.SubjectDTO;
import com.microservice.universitycontentservice.Entity.Semester;
import com.microservice.universitycontentservice.Entity.Subject;
import com.microservice.universitycontentservice.Exceptions.Semester.SemesterServiceException;
import com.microservice.universitycontentservice.Exceptions.Subject.SubjectServiceException;
import com.microservice.universitycontentservice.Repository.SemesterRepository;
import com.microservice.universitycontentservice.Repository.SubjectRepository;
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
public class SubjectService {

    private static final Logger logger = LoggerFactory.getLogger(SubjectService.class);

    private final SubjectRepository subjectRepo;
    private final SemesterRepository semesterRepo;

    @Autowired
    public SubjectService(SubjectRepository subjectRepo, SemesterRepository semesterRepo) {
        this.subjectRepo = subjectRepo;
        this.semesterRepo = semesterRepo;
    }

    public List<SubjectDTO> getAllSubjects() {
        try {
            List<Subject> subjects = subjectRepo.findAll();
            return subjects.stream()
                    .map(SubjectMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (SubjectServiceException ex) {
            throw ex;
        } catch (Exception e) {
            logger.error("Error in getAllSubjects: {}", e.getMessage());
            throw new SubjectServiceException("An error occurred while fetching all subjects. Please try again later.");
        }
    }

    public SubjectDTO createSubject(SubjectDTO subjectDTO) {
        try {
            Optional<Subject> existingSubject = subjectRepo.findByName(subjectDTO.getName());
            if (existingSubject.isPresent()) {
                throw new SubjectServiceException("Subject already exists with this name.");
            }

            semesterRepo.findById(subjectDTO.getSemesterId())
                    .orElseThrow(() -> new SemesterServiceException("Semester not found with this ID."));

            Subject subjectEntity = SubjectMapper.toEntity(subjectDTO);
            Subject savedSubject = subjectRepo.save(subjectEntity);

            return SubjectMapper.toDTO(savedSubject);
        } catch (SubjectServiceException ex) {
            throw ex;
        } catch (Exception e) {
            logger.error("Error in createSubject", e);
            throw new SubjectServiceException("An error occurred while creating the subject. Please try again later.");
        }
    }

    @Transactional
    public void deleteSubject(UUID subjectId) {
        try {
            subjectRepo.findById(subjectId)
                    .orElseThrow(() -> new SubjectServiceException("Subject with ID " + subjectId + " not found."));

            subjectRepo.deleteById(subjectId);

        } catch (SubjectServiceException ex) {
            throw ex;
        } catch (Exception e) {
            logger.error("Error in deleteSubject: {}", e.getMessage());
            throw new SubjectServiceException("An error occurred while deleting the subject. Please try again later.");
        }
    }

    @Transactional
    public SubjectDTO updateSubject(UUID subjectId, Subject updatedSubjectData) {
        try {
            Subject existingSubject = subjectRepo.findById(subjectId)
                    .orElseThrow(() -> new SubjectServiceException("Subject with ID " + subjectId + " not found."));

            existingSubject.setName(updatedSubjectData.getName());
            existingSubject.setShortname(updatedSubjectData.getShortname());
            existingSubject.setCode(updatedSubjectData.getCode());
            existingSubject.setDescription(updatedSubjectData.getDescription());

            Subject updatedSubject = subjectRepo.save(existingSubject);
            return SubjectMapper.toDTO(updatedSubject);

        } catch (SubjectServiceException ex) {
            throw ex;
        } catch (Exception e) {
            logger.error("Error in updateSubject: {}", e.getMessage());
            throw new SubjectServiceException("An error occurred while updating the subject. Please try again later.");
        }
    }
}
