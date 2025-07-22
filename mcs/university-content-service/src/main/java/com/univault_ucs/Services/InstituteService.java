package com.univault_ucs.Services;

import com.univault_ucs.DTO.InstituteDTO;
import com.univault_ucs.DTO.Mapper.InstituteMapper;
import com.univault_ucs.Entity.Institute;
import com.univault_ucs.Exceptions.Institute.InstituteServiceException;
import com.univault_ucs.Repository.InstituteRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Validated
public class InstituteService {

    private static final Logger logger = LoggerFactory.getLogger(InstituteService.class);

    private final InstituteRepository instituteRepo;

    public InstituteService(InstituteRepository instituteRepo) {
        this.instituteRepo = instituteRepo;
    }

    public List<InstituteDTO> getAllInstitutes() {
        try {
            List<Institute> institutes = instituteRepo.findAll();
            return institutes.stream()
                    .map(InstituteMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (InstituteServiceException ex) {
            throw ex;
        }
        catch (Exception e) {
            logger.error("Error in getAllInstitutes: {}", e.getMessage(), e);
            throw new InstituteServiceException("An error occurred while fetching all institutes. Please try again later.");
        }
    }

    public InstituteDTO getInstituteById(UUID id) {
        try {
            Optional<Institute> find = instituteRepo.findById(id);
            if (find.isPresent()) {
                Institute institute = find.get();
                return InstituteMapper.toDTO(institute,false);
            } else {
                throw new InstituteServiceException("Institute not found with ID: " + id);
            }
        } catch (RuntimeException e) {
            logger.error("Error in getInstituteById: {}", e.getMessage(), e);
            throw new InstituteServiceException("Error while fetching institute: " + e.getMessage());
        }
    }

    public InstituteDTO createInstitute(InstituteDTO dto) {
        try {
             Optional<Institute> duplicateRecordChecking = instituteRepo.findByName(dto.getName());

            if(duplicateRecordChecking.isPresent()){
                throw new InstituteServiceException("Institute with this data already exists");
            }

            Institute entity = InstituteMapper.toEntity(dto);

            Institute saved = instituteRepo.save(entity);

            return InstituteMapper.toDTO(saved);
        } catch (Exception e) {
            logger.error("Error in createInstitute", e);
            throw new InstituteServiceException("An error occurred while creating the institute. Please try again later....");
        }
    }

    @Transactional
    public void deleteInstitute(UUID instituteId) {
        try {
            instituteRepo.findById(instituteId)
                    .orElseThrow(() -> new InstituteServiceException("Institute with ID " + instituteId + " not found."));

            instituteRepo.deleteById(instituteId);
        } catch (Exception e) {
            logger.error("Error in deleteInstitute: {}", e.getMessage(), e);
            throw new InstituteServiceException("An error occurred while deleting the institute. Please try again later.");
        }
    }

    @Transactional
    public InstituteDTO updateInstitute(UUID instituteId, Institute updatedInstituteData) {
        try {
            Institute existingInstitute = instituteRepo.findById(instituteId)
                    .orElseThrow(() -> new InstituteServiceException("Institute with ID " + instituteId + " not found."));

            existingInstitute.setName(updatedInstituteData.getName());
            existingInstitute.setShortname(updatedInstituteData.getShortname());
            existingInstitute.setCode(updatedInstituteData.getCode());
            existingInstitute.setDescription(updatedInstituteData.getDescription());

            Institute updated = instituteRepo.save(existingInstitute);
            return InstituteMapper.toDTO(updated);
        } catch (Exception e) {
            logger.error("Error in updateInstitute: {}", e.getMessage(), e);
            throw new InstituteServiceException("An error occurred while updating the institute. Please try again later.");
        }
    }
}
