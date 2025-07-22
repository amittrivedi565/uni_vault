package com.univault_ucs.DTO.Mapper;

import com.univault_ucs.DTO.UnitDTO;
import com.univault_ucs.Entity.Subject;
import com.univault_ucs.Entity.Unit;

public class UnitMapper {

    public static UnitDTO toDTO(Unit unit) {
        if (unit == null) return null;

        UnitDTO dto = mapBasicDTO(unit);

        if (unit.getSubject() != null) {
            dto.setSubjectId(unit.getSubject().getId());
        }

        return dto;
    }

    public static Unit toEntity(UnitDTO dto) {
        if (dto == null) return null;

        Unit unit = mapBasicEntity(dto);

        if (dto.getSubjectId() != null) {
            Subject subject = new Subject();
            subject.setId(dto.getSubjectId());
            unit.setSubject(subject);
        }

        return unit;
    }

    // ---------- Private Helpers ----------

    private static UnitDTO mapBasicDTO(Unit unit) {
        UnitDTO dto = new UnitDTO();
        dto.setId(unit.getId());
        dto.setName(unit.getName());
        dto.setShortname(unit.getShortname());
        dto.setCode(unit.getCode());
        dto.setDescription(unit.getDescription());
        dto.setResource_id(unit.getResourceId());
        dto.setCreatedAt(unit.getCreatedAt());
        dto.setUpdatedAt(unit.getUpdatedAt());
        return dto;
    }

    private static Unit mapBasicEntity(UnitDTO dto) {
        Unit unit = new Unit();
        unit.setId(dto.getId());
        unit.setName(dto.getName());
        unit.setShortname(dto.getShortname());
        unit.setCode(dto.getCode());
        unit.setDescription(dto.getDescription());
        unit.setResourceId(dto.getResource_id());
        unit.setCreatedAt(dto.getCreatedAt());
        unit.setUpdatedAt(dto.getUpdatedAt());
        return unit;
    }
}
