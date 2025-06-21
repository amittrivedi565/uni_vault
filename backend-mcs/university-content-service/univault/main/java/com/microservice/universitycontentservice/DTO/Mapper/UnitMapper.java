package com.microservice.universitycontentservice.DTO.Mapper;

import com.microservice.universitycontentservice.DTO.UnitDTO;
import com.microservice.universitycontentservice.Entity.Subject;
import com.microservice.universitycontentservice.Entity.Unit;

public class UnitMapper {

    public static UnitDTO toDTO(Unit unit) {
        if (unit == null) return null;

        UnitDTO dto = new UnitDTO();
        dto.setId(unit.getId());
        dto.setName(unit.getName());
        dto.setShortname(unit.getShortname());
        dto.setCode(unit.getCode());
        dto.setDescription(unit.getDescription());

        if (unit.getSubject() != null) {
            dto.setSubjectId(unit.getSubject().getId());
        }

        return dto;
    }

    public static Unit toEntity(UnitDTO dto) {
        if (dto == null) return null;

        Unit unit = new Unit();
        unit.setId(dto.getId());
        unit.setName(dto.getName());
        unit.setShortname(dto.getShortname());
        unit.setCode(dto.getCode());
        unit.setDescription(dto.getDescription());

        if (dto.getSubjectId() != null) {
            Subject subject = new Subject();
            subject.setId(dto.getSubjectId());
            unit.setSubject(subject);
        }

        return unit;
    }
}
