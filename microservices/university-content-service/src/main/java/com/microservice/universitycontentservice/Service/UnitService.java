package com.microservice.universitycontentservice.Service;

import com.microservice.universitycontentservice.DTO.Mapper.UnitMapper;
import com.microservice.universitycontentservice.DTO.UnitDTO;
import com.microservice.universitycontentservice.Entity.Subject;
import com.microservice.universitycontentservice.Entity.Unit;
import com.microservice.universitycontentservice.Exceptions.Subject.SubjectServiceException;
import com.microservice.universitycontentservice.Exceptions.Unit.UnitServiceException;
import com.microservice.universitycontentservice.Repository.SubjectRepository;
import com.microservice.universitycontentservice.Repository.UnitRepository;
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
        } catch (Exception e) {
            logger.error("Error in getAllUnits: {}", e.getMessage());
            throw new UnitServiceException("An error occurred while fetching all units. Please try again later.");
        }
    }

    public UnitDTO createUnit(UnitDTO unitDTO) {
        try {
            Optional<Unit> existingUnit = unitRepo.findByName(unitDTO.getName());
            if (existingUnit.isPresent()) {
                throw new UnitServiceException("Unit already exists with this name.");
            }

            Subject subject = subjectRepo.findById(unitDTO.getSubjectId())
                    .orElseThrow(() -> new SubjectServiceException("Subject not found with this ID."));

            Unit unitEntity = UnitMapper.toEntity(unitDTO);
            unitEntity.setSubject(subject);

            Unit savedUnit = unitRepo.save(unitEntity);
            return UnitMapper.toDTO(savedUnit);

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

        } catch (Exception e) {
            logger.error("Error in updateUnit: {}", e.getMessage());
            throw new UnitServiceException("An error occurred while updating the unit. Please try again later.");
        }
    }
}
