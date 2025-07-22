package com.DTO.Mapper;

import com.DTO.SemesterDTO;
import com.Entity.Branch;
import com.Entity.Semester;
import com.Entity.Subject;

import java.util.List;
import java.util.stream.Collectors;

public class SemesterMapper {

    public static SemesterDTO toDTO(Semester semester) {
        if (semester == null) return null;

        SemesterDTO dto = new SemesterDTO();
        dto.setId(semester.getId());
        dto.setName(semester.getName());
        dto.setCode(semester.getCode());
        dto.setSyllabus(semester.getSyllabus());

        if (semester.getBranch() != null) {
            dto.setBranchId(semester.getBranch().getId());
        }

        dto.setSubjects(
                semester.getSubjects() == null ? null :
                        semester.getSubjects().stream()
                                .map(SubjectMapper::toDTO)
                                .collect(Collectors.toList())
        );

        return dto;
    }

    public static Semester toEntity(SemesterDTO dto) {
        if (dto == null) return null;

        Semester semester = new Semester();
        semester.setId(dto.getId());
        semester.setName(dto.getName());
        semester.setCode(dto.getCode());
        semester.setSyllabus(dto.getSyllabus());

        if (dto.getBranchId() != null) {
            Branch branch = new Branch();
            branch.setId(dto.getBranchId());
            semester.setBranch(branch);
        }

        if (dto.getSubjects() != null) {
            List<Subject> subjects = dto.getSubjects().stream()
                    .map(SubjectMapper::toEntity)
                    .peek(subject -> subject.setSemester(semester)) // set back-reference
                    .collect(Collectors.toList());
            semester.setSubjects(subjects);
        }

        return semester;
    }
}
