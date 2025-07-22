package com.univault_ucs.DTO.Mapper;

import com.univault_ucs.DTO.SubjectDTO;
import com.univault_ucs.DTO.UnitDTO;
import com.univault_ucs.Entity.Semester;
import com.univault_ucs.Entity.Subject;
import com.univault_ucs.Entity.Unit;

import java.util.List;
import java.util.stream.Collectors;

public class SubjectMapper {

    public static SubjectDTO toDTO(Subject subject) {
        return toDTO(subject, false);
    }

    public static SubjectDTO toDTO(Subject subject, boolean includeAssociations) {
        if (subject == null) return null;

        SubjectDTO dto = mapBasicDTO(subject);

        if (includeAssociations) {
            dto.setUnits(mapUnitsToDTO(subject.getUnits()));
        }

        return dto;
    }

    public static Subject toEntity(SubjectDTO dto) {
        return toEntity(dto, true);
    }

    public static Subject toEntity(SubjectDTO dto, boolean includeAssociations) {
        if (dto == null) return null;

        Subject subject = mapBasicEntity(dto);

        if (dto.getSemesterId() != null) {
            Semester semester = new Semester();
            semester.setId(dto.getSemesterId());
            subject.setSemester(semester);
        }

        if (includeAssociations) {
            subject.setUnits(mapUnitsToEntity(dto.getUnits(), subject));
        }

        return subject;
    }

    // ---------- Private Helpers ----------

    private static SubjectDTO mapBasicDTO(Subject subject) {
        SubjectDTO dto = new SubjectDTO();
        dto.setId(subject.getId());
        dto.setName(subject.getName());
        dto.setShortname(subject.getShortname());
        dto.setCode(subject.getCode());
        dto.setDescription(subject.getDescription());
        dto.setCreatedAt(subject.getCreatedAt());
        dto.setUpdatedAt(subject.getUpdatedAt());

        if (subject.getSemester() != null) {
            dto.setSemesterId(subject.getSemester().getId());
        }

        return dto;
    }

    private static Subject mapBasicEntity(SubjectDTO dto) {
        Subject subject = new Subject();
        subject.setId(dto.getId());
        subject.setName(dto.getName());
        subject.setShortname(dto.getShortname());
        subject.setCode(dto.getCode());
        subject.setDescription(dto.getDescription());
        subject.setCreatedAt(dto.getCreatedAt());
        subject.setUpdatedAt(dto.getUpdatedAt());
        return subject;
    }

    private static List<UnitDTO> mapUnitsToDTO(List<Unit> units) {
        if (units == null) return null;

        return units.stream()
                .map(UnitMapper::toDTO) // optionally pass `true` if needed
                .collect(Collectors.toList());
    }

    private static List<Unit> mapUnitsToEntity(List<UnitDTO> unitDTOs, Subject subject) {
        if (unitDTOs == null) return null;

        return unitDTOs.stream()
                .map(unitDTO -> {
                    Unit unit = UnitMapper.toEntity(unitDTO);
                    unit.setSubject(subject);
                    return unit;
                })
                .collect(Collectors.toList());
    }
}
