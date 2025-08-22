package com.univault.ims.service;

import com.univault.ims.dto.UnitDTO;

import java.util.List;
import java.util.UUID;

public interface UnitService {
    UnitDTO getUnitById(UUID id);
    List<UnitDTO> getAllUnitsBySubjectId(UUID subjectId);
    UnitDTO createUnit(UnitDTO unitDTO);
    void deleteUnit(UUID unitId);
    UnitDTO updateUnit(UUID unitId, UnitDTO updatedUnitData);
}
