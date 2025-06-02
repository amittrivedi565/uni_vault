package com.microservice.universitycontentservice.Service;

import com.microservice.universitycontentservice.DTO.InstituteDTO;
import com.microservice.universitycontentservice.DTO.Mapper.InstituteMapper;
import com.microservice.universitycontentservice.Entity.Institute;
import com.microservice.universitycontentservice.Exceptions.Institute.InstituteServiceException;
import com.microservice.universitycontentservice.Repository.InstituteRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InstituteService {

    private static final Logger logger = LoggerFactory.getLogger(InstituteService.class);

    private final InstituteRepository instituteRepo;

    public InstituteService(InstituteRepository instituteRepo){
        this.instituteRepo = instituteRepo;
    }

    public List<InstituteDTO> getAllInstitutesService() {
        try {
            List<Institute> fetchedInstitutes = instituteRepo.findAll();

            if (fetchedInstitutes.isEmpty()) {
                logger.info("No institutes found in the database.");
                return List.of();
            }

            return fetchedInstitutes.stream()
                    .map(InstituteMapper::toDTO)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            logger.error("Error fetching institutes: {}", e.getMessage(), e);
            throw new InstituteServiceException("An error occurred while fetching all institutes. Please try again later.");
        }
    }

    public InstituteDTO postInstituteService(InstituteDTO institute) {
        try {
            Optional<Institute> existingInstitute = instituteRepo.findByName(institute.getName());

            if (existingInstitute.isPresent()) {
                throw new InstituteServiceException("Institute " + institute.getName() + " already exists.");
            }

            Institute entity = InstituteMapper.toEntity(institute);

            Institute saved = instituteRepo.save(entity);

            return InstituteMapper.toDTO(saved);

        } catch (Exception e) {
            logger.error("Unexpected error in postInstituteService: {}", e.getMessage(), e);
            throw new InstituteServiceException("An error occurred while creating the institute. Please try again later.");
        }
    }

    @Transactional
    public void deleteInstituteService(UUID instituteId) {
        try {

            instituteRepo.findById(instituteId)
                    .orElseThrow(() -> new InstituteServiceException("Institute with ID " + instituteId + " not found."));
            instituteRepo.deleteById(instituteId);

        } catch (Exception e) {
            logger.error("Unexpected error in deleteInstituteService: {}", e.getMessage(), e);
            throw new InstituteServiceException("An error occurred while deleting the institute. Please try again later.");
        }
    }

    @Transactional
    public InstituteDTO updateInstituteService(UUID instituteId, Institute updatedInstituteData) {
        try {
            Institute existingInstitute = instituteRepo.findById(instituteId)
                    .orElseThrow(() -> new InstituteServiceException("Institute with ID " + instituteId + " not found."));

            existingInstitute.setName(updatedInstituteData.getName());
            existingInstitute.setShortname(updatedInstituteData.getShortname());
            existingInstitute.setCode(updatedInstituteData.getCode());
            existingInstitute.setDescription(updatedInstituteData.getDescription());

            Institute updatedInstitute = instituteRepo.save(existingInstitute);
            return InstituteMapper.toDTO(updatedInstitute);

        } catch (Exception e) {
            logger.error("Unexpected error in updateInstituteService: {}", e.getMessage(), e);
            throw new InstituteServiceException("An error occurred while updating the institute. Please try again later.");
        }
    }
}
