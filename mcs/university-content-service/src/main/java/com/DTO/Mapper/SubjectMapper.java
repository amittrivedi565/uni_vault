package com.DTO.Mapper;

import com.DTO.SubjectDTO;
import com.Entity.Semester;
import com.Entity.Subject;
import com.Entity.Unit;

import java.util.List;
import java.util.stream.Collectors;

public class SubjectMapper {

    public static SubjectDTO toDTO(Subject subject) {
        return toDTO(subject, false); // default: no units
    }

    public static SubjectDTO toDTO(Subject subject, boolean includeAssociations) {
        if (subject == null) return null;

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

        if (includeAssociations && subject.getUnits() != null) {
            dto.setUnits(
                    subject.getUnits().stream()
                            .map(unit -> UnitMapper.toDTO(unit)) // or UnitMapper.toDTO(unit, false) if nested logic needed
                            .collect(Collectors.toList())
            );
        } else {
            dto.setUnits(null);
        }

        return dto;
    }

    public static Subject toEntity(SubjectDTO dto) {
        return toEntity(dto, false); // default: no units
    }

    public static Subject toEntity(SubjectDTO dto, boolean includeAssociations) {
        if (dto == null) return null;

        Subject subject = new Subject();
        subject.setId(dto.getId());
        subject.setName(dto.getName());
        subject.setShortname(dto.getShortname());
        subject.setCode(dto.getCode());
        subject.setDescription(dto.getDescription());

        subject.setCreatedAt(dto.getCreatedAt());
        subject.setUpdatedAt(dto.getUpdatedAt());

        if (dto.getSemesterId() != null) {
            Semester semester = new Semester();
            semester.setId(dto.getSemesterId());
            subject.setSemester(semester);
        }

        if (includeAssociations && dto.getUnits() != null) {
            List<Unit> units = dto.getUnits().stream()
                    .map(unitDTO -> {
                        Unit unit = UnitMapper.toEntity(unitDTO);
                        unit.setSubject(subject);
                        return unit;
                    })
                    .collect(Collectors.toList());
            subject.setUnits(units);
        }

        return subject;
    }
}
