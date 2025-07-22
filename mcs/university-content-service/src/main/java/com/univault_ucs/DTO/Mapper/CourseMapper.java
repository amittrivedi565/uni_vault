package com.univault_ucs.DTO.Mapper;

import com.univault_ucs.DTO.BranchDTO;
import com.univault_ucs.DTO.CourseDTO;
import com.univault_ucs.Entity.Branch;
import com.univault_ucs.Entity.Course;
import com.univault_ucs.Entity.Institute;

import java.util.List;
import java.util.stream.Collectors;

public class CourseMapper {

    public static CourseDTO toDTO(Course course) {
        return toDTO(course, false);
    }

    public static CourseDTO toDTO(Course course, boolean includeAssociations) {
        if (course == null) return null;

        CourseDTO dto = basicDTO(course);

        if (includeAssociations) {
            dto.setBranches(mapBranchesToDTO(course.getBranches()));
        }

        return dto;
    }

    public static Course toEntity(CourseDTO dto) {
        return toEntity(dto, true);
    }

    public static Course toEntity(CourseDTO dto, boolean includeAssociations) {
        if (dto == null) return null;

        Course course = basicEntity(dto);

        if (includeAssociations) {
            course.setBranches(mapBranchesToEntity(dto.getBranches(), course));
        }

        return course;
    }

    // ----- Private Helper Methods -----

    private static CourseDTO basicDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setShortname(course.getShortname());
        dto.setCode(course.getCode());
        dto.setDescription(course.getDescription());
        dto.setCreatedAt(course.getCreatedAt());
        dto.setUpdatedAt(course.getUpdatedAt());

        if (course.getInstitute() != null) {
            dto.setInstituteId(course.getInstitute().getId());
        }

        return dto;
    }

    private static Course basicEntity(CourseDTO dto) {
        Course course = new Course();
        course.setId(dto.getId());
        course.setName(dto.getName());
        course.setShortname(dto.getShortname());
        course.setCode(dto.getCode());
        course.setDescription(dto.getDescription());
        course.setCreatedAt(dto.getCreatedAt());
        course.setUpdatedAt(dto.getUpdatedAt());

        if (dto.getInstituteId() != null) {
            Institute institute = new Institute();
            institute.setId(dto.getInstituteId());
            course.setInstitute(institute);
        }

        return course;
    }

    private static List<BranchDTO> mapBranchesToDTO(List<Branch> branches) {
        if (branches == null) return null;

        return branches.stream()
                .map(branch -> BranchMapper.toDTO(branch, true))
                .collect(Collectors.toList());
    }

    private static List<Branch> mapBranchesToEntity(List<BranchDTO> branchDTOs, Course course) {
        if (branchDTOs == null) return null;

        return branchDTOs.stream()
                .map(branchDTO -> BranchMapper.toEntity(branchDTO, course, true))
                .collect(Collectors.toList());
    }
}
