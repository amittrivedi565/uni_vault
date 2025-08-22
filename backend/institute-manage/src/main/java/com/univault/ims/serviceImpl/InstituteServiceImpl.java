package com.univault.ims.serviceImpl;

import com.univault.ims.dao.InstituteDao;
import com.univault.ims.dto.InstituteDTO;
import com.univault.ims.dto.Mapper.InstituteMapper;
import com.univault.ims.entity.Institute;
import com.univault.ims.exception.service.InstituteServiceException;
import com.univault.ims.service.InstituteService;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Validated
public class InstituteServiceImpl implements InstituteService {

    private static final Logger logger = LoggerFactory.getLogger(InstituteServiceImpl.class);

    private final InstituteDao instituteDao;

    public InstituteServiceImpl(InstituteDao instituteDao) {
        this.instituteDao = instituteDao;
    }

    @Transactional
    @Override
    @Cacheable(value = "institutesCache")
    public List<InstituteDTO> getAllInstitutes() {
        try {
            List<Institute> institutes = instituteDao.findAll();
            if (institutes.isEmpty()) {
                String message = "No institutes found.";
                logger.warn(message);
                throw new InstituteServiceException(message);
            }
            return institutes.stream()
                    .map(i -> InstituteMapper.toDTO(i, true))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in getAllInstitutes", e);
            throw new InstituteServiceException("An error occurred while fetching all institutes.", e);
        }
    }

    @Override
    public InstituteDTO getInstituteById(UUID id) {
        return instituteDao.findById(id)
                .map(institute -> InstituteMapper.toDTO(institute, false))
                .orElseThrow(() -> {
                    String message = "Institute not found with ID: " + id;
                    logger.warn(message);
                    return new InstituteServiceException(message);
                });
    }

    @Override
    public InstituteDTO createInstitute(InstituteDTO dto) {
        instituteDao.findByName(dto.getName())
                .ifPresent(existing -> {
                    String message = "Institute already exists with name: " + dto.getName();
                    logger.warn(message);
                    throw new InstituteServiceException(message);
                });

        try {
            Institute entity = InstituteMapper.toEntity(dto);
            Institute saved = instituteDao.save(entity);
            logger.info("Institute created successfully with ID: {}", saved.getId());
            return InstituteMapper.toDTO(saved);
        } catch (Exception e) {
            logger.error("Error in createInstitute", e);
            throw new InstituteServiceException("An error occurred while creating the institute.", e);
        }
    }

    @Override
    @Transactional
    public void deleteInstitute(UUID instituteId) {
        Institute institute = instituteDao.findById(instituteId)
                .orElseThrow(() -> {
                    String message = "Institute not found with ID: " + instituteId;
                    logger.warn(message);
                    return new InstituteServiceException(message);
                });

        try {
            instituteDao.delete(institute);
            logger.info("Institute deleted successfully with ID: {}", instituteId);
        } catch (Exception e) {
            logger.error("Error in deleteInstitute", e);
            throw new InstituteServiceException("An error occurred while deleting the institute.", e);
        }
    }

    @Override
    @Transactional
    public InstituteDTO updateInstitute(UUID instituteId, InstituteDTO updatedInstituteData) {
        Institute existingInstitute = instituteDao.findById(instituteId)
                .orElseThrow(() -> {
                    String message = "Institute not found with ID: " + instituteId;
                    logger.warn(message);
                    return new InstituteServiceException(message);
                });

        existingInstitute.setName(updatedInstituteData.getName());
        existingInstitute.setShortname(updatedInstituteData.getShortname());
        existingInstitute.setCode(updatedInstituteData.getCode());
        existingInstitute.setDescription(updatedInstituteData.getDescription());

        try {
            Institute updatedInstitute = instituteDao.save(existingInstitute);
            logger.info("Institute updated successfully with ID: {}", instituteId);
            return InstituteMapper.toDTO(updatedInstitute);
        } catch (Exception e) {
            logger.error("Error in updateInstitute", e);
            throw new InstituteServiceException("An error occurred while updating the institute.", e);
        }
    }
}
