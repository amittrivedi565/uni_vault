package com.DTO.Mapper;

import com.DTO.SubjectDTO;
import com.Entity.Semester;
import com.Entity.Subject;
import com.Entity.Unit;

import java.util.List;
import java.util.stream.Collectors;

public class SubjectMapper {

    public static SubjectDTO toDTO(Subject subject) {
        if (subject == null) return null;

        SubjectDTO dto = new SubjectDTO();
        dto.setId(subject.getId());
        dto.setName(subject.getName());
        dto.setShortname(subject.getShortname());
        dto.setCode(subject.getCode());
        dto.setDescription(subject.getDescription());

        if (subject.getSemester() != null) {
            dto.setSemesterId(subject.getSemester().getId());
        }

        dto.setUnits(
                subject.getUnits() == null ? null :
                        subject.getUnits().stream()
                                .map(UnitMapper::toDTO)
                                .collect(Collectors.toList())
        );

        return dto;
    }

    public static Subject toEntity(SubjectDTO dto) {
        if (dto == null) return null;

        Subject subject = new Subject();
        subject.setId(dto.getId());
        subject.setName(dto.getName());
        subject.setShortname(dto.getShortname());
        subject.setCode(dto.getCode());
        subject.setDescription(dto.getDescription());

        if (dto.getSemesterId() != null) {
            Semester semester = new Semester();
            semester.setId(dto.getSemesterId());
            subject.setSemester(semester);
        }

        if (dto.getUnits() != null) {
            List<Unit> units = dto.getUnits().stream()
                    .map(UnitMapper::toEntity)
                    .peek(unit -> unit.setSubject(subject)) // set back-reference
                    .collect(Collectors.toList());
            subject.setUnits(units);
        }

        return subject;
    }
}
