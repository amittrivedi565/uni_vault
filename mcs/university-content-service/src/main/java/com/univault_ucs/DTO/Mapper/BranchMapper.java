package com.univault_ucs.DTO.Mapper;

import com.univault_ucs.DTO.BranchDTO;
import com.univault_ucs.DTO.SemesterDTO;
import com.univault_ucs.Entity.Branch;
import com.univault_ucs.Entity.Course;
import com.univault_ucs.Entity.Semester;

import java.util.List;
import java.util.stream.Collectors;

public class BranchMapper {

    public static BranchDTO toDTO(Branch branch) {
        return toDTO(branch, false);
    }

    public static BranchDTO toDTO(Branch branch, boolean includeAssociations) {
        if (branch == null) return null;

        BranchDTO dto = basicDTO(branch);

        if (includeAssociations) {
            dto.setSemesters(mapSemestersToDTO(branch.getSemesters()));
        }

        return dto;
    }

    public static Branch toEntity(BranchDTO dto, Course course) {
        return toEntity(dto, course, false);
    }

    public static Branch toEntity(BranchDTO dto, Course course, boolean includeAssociations) {
        if (dto == null) return null;

        Branch branch = basicEntity(dto, course);

        if (includeAssociations) {
            List<Semester> semesters = mapSemestersToEntity(dto.getSemesters(), branch);
            branch.setSemesters(semesters);
        }

        return branch;
    }

    // ----- Helper Methods -----

    private static BranchDTO basicDTO(Branch branch) {
        BranchDTO dto = new BranchDTO();
        dto.setId(branch.getId());
        dto.setName(branch.getName());
        dto.setShortname(branch.getShortname());
        dto.setCode(branch.getCode());
        dto.setDescription(branch.getDescription());
        dto.setCreatedAt(branch.getCreatedAt());
        dto.setUpdatedAt(branch.getUpdatedAt());

        if (branch.getCourse() != null) {
            dto.setCourseId(branch.getCourse().getId());
        }

        return dto;
    }

    private static Branch basicEntity(BranchDTO dto, Course course) {
        Branch branch = new Branch();
        branch.setId(dto.getId());
        branch.setName(dto.getName());
        branch.setShortname(dto.getShortname());
        branch.setCode(dto.getCode());
        branch.setDescription(dto.getDescription());
        branch.setCreatedAt(dto.getCreatedAt());
        branch.setUpdatedAt(dto.getUpdatedAt());
        branch.setCourse(course);
        return branch;
    }

    private static List<Semester> mapSemestersToEntity(List<SemesterDTO> semesterDTOs, Branch branch) {
        if (semesterDTOs == null) return null;

        return semesterDTOs.stream()
                .map(dto -> {
                    Semester semester = SemesterMapper.toEntity(dto, true);
                    semester.setBranch(branch);
                    return semester;
                })
                .collect(Collectors.toList());
    }

    private static List<SemesterDTO> mapSemestersToDTO(List<Semester> semesters) {
        if (semesters == null) return null;

        return semesters.stream()
                .map(semester -> SemesterMapper.toDTO(semester, true))
                .collect(Collectors.toList());
    }
}
