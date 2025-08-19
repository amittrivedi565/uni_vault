package com.univault.ims.dto.Mapper;

import com.univault.ims.dto.BranchDTO;
import com.univault.ims.dto.SemesterDTO;
import com.univault.ims.entity.Branch;
import com.univault.ims.entity.Course;
import com.univault.ims.entity.Semester;

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

    public static Branch toEntity(BranchDTO dto) {
        return toEntity(dto, null, false);
    }

    public static Branch toEntity(BranchDTO dto, Course parentCourse) {
        return toEntity(dto, parentCourse, false);
    }

    public static Branch toEntity(BranchDTO dto, Course parentCourse, boolean includeAssociations) {
        if (dto == null) return null;

        Branch branch = basicEntity(dto);

        if (parentCourse != null) {
            branch.setCourse(parentCourse);
        } else if (dto.getCourseId() != null) {
            Course course = new Course();
            course.setId(dto.getCourseId());
            branch.setCourse(course);
        }

        if (includeAssociations) {
            branch.setSemesters(mapSemestersToEntity(dto.getSemesters(), branch));
        }

        return branch;
    }

    // ----- Private Helper Methods -----

    private static BranchDTO basicDTO(Branch branch) {
        BranchDTO dto = new BranchDTO();
        dto.setId(branch.getId());
        dto.setName(branch.getName());
        dto.setCode(branch.getCode());
        dto.setDescription(branch.getDescription());
        dto.setCreatedAt(branch.getCreatedAt());
        dto.setUpdatedAt(branch.getUpdatedAt());

        if (branch.getCourse() != null) {
            dto.setCourseId(branch.getCourse().getId());
        }

        return dto;
    }

    private static Branch basicEntity(BranchDTO dto) {
        Branch branch = new Branch();
        branch.setId(dto.getId());
        branch.setName(dto.getName());
        branch.setCode(dto.getCode());
        branch.setDescription(dto.getDescription());
        branch.setCreatedAt(dto.getCreatedAt());
        branch.setUpdatedAt(dto.getUpdatedAt());
        return branch;
    }

    private static List<SemesterDTO> mapSemestersToDTO(List<Semester> semesters) {
        if (semesters == null) return List.of();

        return semesters.stream()
                .map(SemesterMapper::toDTO)
                .collect(Collectors.toList());
    }

    private static List<Semester> mapSemestersToEntity(List<SemesterDTO> semesterDTOs, Branch branch) {
        if (semesterDTOs == null) return List.of();

        return semesterDTOs.stream()
                .map(dto -> SemesterMapper.toEntity(dto, branch))
                .collect(Collectors.toList());
    }
}
