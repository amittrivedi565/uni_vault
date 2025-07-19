package com.DTO.Mapper;

import com.DTO.SemesterDTO;
import com.Entity.Branch;
import com.Entity.Semester;
import com.Entity.Subject;

import java.util.List;
import java.util.stream.Collectors;

public class SemesterMapper {

    public static SemesterDTO toDTO(Semester semester) {
        return toDTO(semester, false); // default: exclude associations
    }

    public static SemesterDTO toDTO(Semester semester, boolean includeAssociations) {
        if (semester == null) return null;

        SemesterDTO dto = new SemesterDTO();
        dto.setId(semester.getId());
        dto.setName(semester.getName());
        dto.setCode(semester.getCode());
        dto.setSyllabus(semester.getSyllabus());
        dto.setCreatedAt(semester.getCreatedAt());
        dto.setUpdatedAt(semester.getUpdatedAt());

        if (semester.getBranch() != null) {
            dto.setBranchId(semester.getBranch().getId());
        }

        if (includeAssociations && semester.getSubjects() != null) {
            dto.setSubjects(
                    semester.getSubjects().stream()
                            .map(subject -> SubjectMapper.toDTO(subject, true)) // optional deep nesting
                            .collect(Collectors.toList())
            );
        } else {
            dto.setSubjects(null);
        }

        return dto;
    }

    public static Semester toEntity(SemesterDTO dto) {
        return toEntity(dto, true);
    }

    public static Semester toEntity(SemesterDTO dto, boolean includeAssociations) {
        if (dto == null) return null;

        Semester semester = new Semester();
        semester.setId(dto.getId());
        semester.setName(dto.getName());
        semester.setCode(dto.getCode());
        semester.setSyllabus(dto.getSyllabus());
        semester.setCreatedAt(dto.getCreatedAt());
        semester.setUpdatedAt(dto.getUpdatedAt());

        if (dto.getBranchId() != null) {
            Branch branch = new Branch();
            branch.setId(dto.getBranchId());
            semester.setBranch(branch);
        }

        if (includeAssociations && dto.getSubjects() != null) {
            List<Subject> subjects = dto.getSubjects().stream()
                    .map(subjectDTO -> {
                        Subject subject = SubjectMapper.toEntity(subjectDTO, true); // optional nesting
                        subject.setSemester(semester);
                        return subject;
                    })
                    .collect(Collectors.toList());
            semester.setSubjects(subjects);
        }

        return semester;
    }
}
