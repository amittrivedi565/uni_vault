package com.univault.ims.serviceImpl;

import com.univault.ims.dao.SubjectDao;
import com.univault.ims.dao.UnitDao;
import com.univault.ims.dto.Mapper.UnitMapper;
import com.univault.ims.dto.UnitDTO;
import com.univault.ims.entity.Subject;
import com.univault.ims.entity.Unit;
import com.univault.ims.exception.service.UnitServiceException;
import com.univault.ims.service.UnitService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UnitServiceImpl implements UnitService {

    private static final Logger logger = LoggerFactory.getLogger(UnitServiceImpl.class);

    private final UnitDao unitDao;
    private final SubjectDao subjectDao;

    public UnitServiceImpl(UnitDao unitDao, SubjectDao subjectDao) {
        this.unitDao = unitDao;
        this.subjectDao = subjectDao;
    }

    @Override
    public UnitDTO getUnitById(UUID id) {
        return unitDao.findById(id)
                .map(UnitMapper::toDTO)
                .orElseThrow(() -> {
                    String message = "Unit not found with ID: " + id;
                    logger.warn(message);
                    return new UnitServiceException(message);
                });
    }

    @Override
    public List<UnitDTO> getAllUnitsBySubjectId(UUID subjectId) {
        try {
            List<Unit> units = unitDao.findAllUnitsBySubjectId(subjectId);
            if (units.isEmpty()) {
                String message = "No Units found for Subject ID: " + subjectId;
                logger.warn(message);
                throw new UnitServiceException(message);
            }
            return units.stream()
                    .map(UnitMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in getAllUnitsBySubjectId", e);
            throw new UnitServiceException("An error occurred while fetching units by subject id.", e);
        }
    }

    @Override
    public UnitDTO createUnit(UnitDTO unitDTO) {
        unitDao.findByNameAndSubjectId(unitDTO.getName(), unitDTO.getSubjectId())
                .ifPresent(existing -> {
                    String message = "Unit already exists with name: " + unitDTO.getName();
                    logger.warn(message);
                    throw new UnitServiceException(message);
                });

        Subject subject = subjectDao.findById(unitDTO.getSubjectId())
                .orElseThrow(() -> {
                    String message = "Subject not found with ID: " + unitDTO.getSubjectId();
                    logger.warn(message);
                    return new UnitServiceException(message);
                });

        try {
            Unit unitEntity = UnitMapper.toEntity(unitDTO);
            unitEntity.setSubject(subject);

            Unit savedUnit = unitDao.save(unitEntity);
            logger.info("Unit created successfully with ID: {}", savedUnit.getId());
            return UnitMapper.toDTO(savedUnit);
        } catch (Exception e) {
            logger.error("Error in createUnit", e);
            throw new UnitServiceException("An error occurred while creating the unit.", e);
        }
    }

    @Override
    @Transactional
    public void deleteUnit(UUID unitId) {
        Unit unit = unitDao.findById(unitId)
                .orElseThrow(() -> {
                    String message = "Unit not found with ID: " + unitId;
                    logger.warn(message);
                    return new UnitServiceException(message);
                });

        try {
            unitDao.delete(unit);
            logger.info("Unit deleted successfully with ID: {}", unitId);
        } catch (Exception e) {
            logger.error("Error in deleteUnit", e);
            throw new UnitServiceException("An error occurred while deleting the unit.", e);
        }
    }

    @Override
    @Transactional
    public UnitDTO updateUnit(UUID unitId, UnitDTO updatedUnitData) {
        Unit existingUnit = unitDao.findById(unitId)
                .orElseThrow(() -> {
                    String message = "Unit not found with ID: " + unitId;
                    logger.warn(message);
                    return new UnitServiceException(message);
                });

        existingUnit.setName(updatedUnitData.getName());
        existingUnit.setShortname(updatedUnitData.getShortname());
        existingUnit.setCode(updatedUnitData.getCode());
        existingUnit.setDescription(updatedUnitData.getDescription());

        try {
            Unit updatedUnit = unitDao.save(existingUnit);
            logger.info("Unit updated successfully with ID: {}", unitId);
            return UnitMapper.toDTO(updatedUnit);
        } catch (Exception e) {
            logger.error("Error in updateUnit", e);
            throw new UnitServiceException("An error occurred while updating the unit.", e);
        }
    }
}
