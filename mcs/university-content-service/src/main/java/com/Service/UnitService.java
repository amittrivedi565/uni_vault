package com.Service;

import com.DTO.Mapper.UnitMapper;
import com.DTO.UnitDTO;
import com.Entity.Subject;
import com.Entity.Unit;
import com.Exceptions.Unit.UnitServiceException;
import com.Repository.SubjectRepository;
import com.Repository.UnitRepository;
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
public class UnitService {

    private static final Logger logger = LoggerFactory.getLogger(UnitService.class);

    private final UnitRepository unitRepo;
    private final SubjectRepository subjectRepo;

    @Autowired
    public UnitService(UnitRepository unitRepo, SubjectRepository subjectRepo) {
        this.unitRepo = unitRepo;
        this.subjectRepo = subjectRepo;
    }

    public List<UnitDTO> getAllUnits() {
        try {
            List<Unit> units = unitRepo.findAll();
            return units.stream()
                    .map(UnitMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (UnitServiceException ex) {
            throw ex;
        } catch (Exception e) {
            logger.error("Error in getAllUnits: {}", e.getMessage());
            throw new UnitServiceException("An error occurred while fetching all units. Please try again later.");
        }
    }

    public UnitDTO createUnit(UnitDTO unitDTO) {
        try {
            Optional<Unit> existingUnit = unitRepo.findByNameAndSubjectId(unitDTO.getName(),unitDTO.getSubjectId());
            if (existingUnit.isPresent()) {
                throw new UnitServiceException("Unit already exists with this name.");
            }

            Subject subject = subjectRepo.findById(unitDTO.getSubjectId())
                    .orElseThrow(() -> new UnitServiceException("Subject not found with this ID."));

            Unit unitEntity = UnitMapper.toEntity(unitDTO);
            unitEntity.setSubject(subject);

            Unit savedUnit = unitRepo.save(unitEntity);
            return UnitMapper.toDTO(savedUnit);

        } catch (UnitServiceException ex) {
            throw ex;
        } catch (Exception e) {
            logger.error("Error in createUnit", e);
            throw new UnitServiceException("An error occurred while creating the unit. Please try again later.");
        }
    }

    @Transactional
    public void deleteUnit(UUID unitId) {
        try {
            unitRepo.findById(unitId)
                    .orElseThrow(() -> new UnitServiceException("Unit with ID " + unitId + " not found."));

            unitRepo.deleteById(unitId);

        } catch (UnitServiceException ex) {
            throw ex;
        } catch (Exception e) {
            logger.error("Error in deleteUnit: {}", e.getMessage());
            throw new UnitServiceException("An error occurred while deleting the unit. Please try again later.");
        }
    }

    @Transactional
    public UnitDTO updateUnit(UUID unitId, Unit updatedUnitData) {
        try {
            Unit existingUnit = unitRepo.findById(unitId)
                    .orElseThrow(() -> new UnitServiceException("Unit with ID " + unitId + " not found."));

            existingUnit.setName(updatedUnitData.getName());
            existingUnit.setShortname(updatedUnitData.getShortname());
            existingUnit.setCode(updatedUnitData.getCode());
            existingUnit.setDescription(updatedUnitData.getDescription());

            Unit updatedUnit = unitRepo.save(existingUnit);
            return UnitMapper.toDTO(updatedUnit);

        } catch (UnitServiceException ex) {
            throw ex;
        } catch (Exception e) {
            logger.error("Error in updateUnit: {}", e.getMessage());
            throw new UnitServiceException("An error occurred while updating the unit. Please try again later.");
        }
    }
}
