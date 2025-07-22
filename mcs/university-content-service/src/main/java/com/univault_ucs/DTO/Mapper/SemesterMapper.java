package com.univault_ucs.DTO.Mapper;

import com.univault_ucs.DTO.SemesterDTO;
import com.univault_ucs.DTO.SubjectDTO;
import com.univault_ucs.Entity.Branch;
import com.univault_ucs.Entity.Semester;
import com.univault_ucs.Entity.Subject;

import java.util.List;
import java.util.stream.Collectors;

public class SemesterMapper {

    public static SemesterDTO toDTO(Semester semester) {
        return toDTO(semester, false);
    }

    public static SemesterDTO toDTO(Semester semester, boolean includeAssociations) {
        if (semester == null) return null;

        SemesterDTO dto = mapBasicDTO(semester);

        if (includeAssociations) {
            dto.setSubjects(mapSubjectsToDTO(semester.getSubjects()));
        }

        return dto;
    }

    public static Semester toEntity(SemesterDTO dto) {
        return toEntity(dto, true);
    }

    public static Semester toEntity(SemesterDTO dto, boolean includeAssociations) {
        if (dto == null) return null;

        Semester semester = mapBasicEntity(dto);

        if (dto.getBranchId() != null) {
            Branch branch = new Branch();
            branch.setId(dto.getBranchId());
            semester.setBranch(branch);
        }

        if (includeAssociations) {
            semester.setSubjects(mapSubjectsToEntity(dto.getSubjects(), semester));
        }

        return semester;
    }

    // ---------- Private Helpers ----------

    private static SemesterDTO mapBasicDTO(Semester semester) {
        SemesterDTO dto = new SemesterDTO();
        dto.setId(semester.getId());
        dto.setName(semester.getName());
        dto.setCode(semester.getCode());
        dto.setResource_id(semester.getResource_id());
        dto.setCreatedAt(semester.getCreatedAt());
        dto.setUpdatedAt(semester.getUpdatedAt());

        if (semester.getBranch() != null) {
            dto.setBranchId(semester.getBranch().getId());
        }

        return dto;
    }

    private static Semester mapBasicEntity(SemesterDTO dto) {
        Semester semester = new Semester();
        semester.setId(dto.getId());
        semester.setName(dto.getName());
        semester.setCode(dto.getCode());
        semester.setResource_id(dto.getResource_id());
        semester.setCreatedAt(dto.getCreatedAt());
        semester.setUpdatedAt(dto.getUpdatedAt());
        return semester;
    }

    private static List<SubjectDTO> mapSubjectsToDTO(List<Subject> subjects) {
        if (subjects == null) return null;

        return subjects.stream()
                .map(subject -> SubjectMapper.toDTO(subject, true)) // optional deep nesting
                .collect(Collectors.toList());
    }

    private static List<Subject> mapSubjectsToEntity(List<SubjectDTO> subjectDTOs, Semester semester) {
        if (subjectDTOs == null) return null;

        return subjectDTOs.stream()
                .map(subjectDTO -> {
                    Subject subject = SubjectMapper.toEntity(subjectDTO, true);
                    subject.setSemester(semester);
                    return subject;
                })
                .collect(Collectors.toList());
    }
}
