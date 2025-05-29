package com.service.src.Service;

import com.service.src.Dto.Mapper.instituteMapper;
import com.service.src.Dto.Response.instituteResponseDto;
import com.service.src.Entity.instituteEntity;
import com.service.src.Exceptions.Institute.instituteAlreadyExistsException;
import com.service.src.Exceptions.Institute.instituteNotFoundException;
import com.service.src.Exceptions.Institute.instituteServiceException;
import com.service.src.Repository.instituteRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class instituteService {

    private static final Logger logger = LoggerFactory.getLogger(instituteService.class);

    private final instituteRepository instituteRepo;

    public instituteService(instituteRepository instituteRepo){
        this.instituteRepo = instituteRepo;
    }

    public List<instituteResponseDto> getAllInstitutesService() {
        try {
            List<instituteEntity> fetchedInstitutes = instituteRepo.findAll();

            if (fetchedInstitutes.isEmpty()) {
                logger.info("No institutes found in the database.");
                return List.of();
            }

            return fetchedInstitutes.stream()
                    .map(instituteMapper::toDto)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            logger.error("Error fetching institutes: {}", e.getMessage(), e);
            throw new instituteServiceException("An error occurred while fetching all institutes. Please try again later.");
        }
    }

    public instituteResponseDto postInstituteService(instituteEntity institute) {
        try {
            Optional<instituteEntity> existingInstitute = instituteRepo.findByName(institute.getName());

            if (existingInstitute.isPresent()) {
                throw new instituteAlreadyExistsException("Institute " + institute.getName() + " already exists.");
            }

            instituteEntity savedInstitute = instituteRepo.save(institute);
            return instituteMapper.toDto(savedInstitute);

        } catch (DataAccessException e) {
            logger.error("DataAccessException in postInstituteService: {}", e.getMessage(), e);
            throw new instituteServiceException("An error has occurred while accessing the database.");
        } catch (instituteAlreadyExistsException e) {
            logger.warn("Institute already exists: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error in postInstituteService: {}", e.getMessage(), e);
            throw new instituteServiceException("An error occurred while creating the institute. Please try again later.");
        }
    }

    @Transactional
    public void deleteInstituteService(UUID instituteId) {
        try {
            instituteRepo.findById(instituteId)
                    .orElseThrow(() -> new instituteNotFoundException("Institute with ID " + instituteId + " not found."));
            instituteRepo.deleteById(instituteId);
        } catch (DataAccessException e) {
            logger.error("DataAccessException in deleteInstituteService: {}", e.getMessage(), e);
            throw new instituteServiceException("An error has occurred while accessing the database.");
        } catch (instituteNotFoundException e) {
            logger.warn("Institute with ID {} not found: {}", instituteId, e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error in deleteInstituteService: {}", e.getMessage(), e);
            throw new instituteServiceException("An error occurred while deleting the institute. Please try again later.");
        }
    }

    @Transactional
    public instituteResponseDto updateInstituteService(UUID instituteId, instituteEntity updatedInstituteData) {
        try {
            instituteEntity existingInstitute = instituteRepo.findById(instituteId)
                    .orElseThrow(() -> new instituteNotFoundException("Institute with ID " + instituteId + " not found."));

            existingInstitute.setName(updatedInstituteData.getName());
            existingInstitute.setShortname(updatedInstituteData.getShortname());
            existingInstitute.setCode(updatedInstituteData.getCode());
            existingInstitute.setDescription(updatedInstituteData.getDescription());

            instituteEntity updatedInstitute = instituteRepo.save(existingInstitute);
            return instituteMapper.toDto(updatedInstitute);

        } catch (DataAccessException e) {
            logger.error("DataAccessException in updateInstituteService: {}", e.getMessage(), e);
            throw new instituteServiceException("An error has occurred while accessing the database.");
        } catch (instituteNotFoundException e) {
            logger.warn("Institute with ID {} not found: {}", instituteId, e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error in updateInstituteService: {}", e.getMessage(), e);
            throw new instituteServiceException("An error occurred while updating the institute. Please try again later.");
        }
    }
}
