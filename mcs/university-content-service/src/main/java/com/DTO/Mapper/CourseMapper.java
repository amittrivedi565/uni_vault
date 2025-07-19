package com.DTO.Mapper;

import com.DTO.CourseDTO;
import com.Entity.Branch;
import com.Entity.Course;
import com.Entity.Institute;

import java.util.List;
import java.util.stream.Collectors;

public class CourseMapper {

    public static CourseDTO toDTO(Course course) {
        return toDTO(course, false); // default: no associations
    }

    public static CourseDTO toDTO(Course course, boolean includeAssociations) {
        if (course == null) return null;

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

        if (includeAssociations && course.getBranches() != null) {
            dto.setBranches(
                    course.getBranches().stream()
                            .map(branch -> BranchMapper.toDTO(branch, true)) // include nested only if needed
                            .collect(Collectors.toList())
            );
        } else {
            dto.setBranches(null); // or skip this if using @JsonInclude
        }

        return dto;
    }

    public static Course toEntity(CourseDTO dto) {
        return toEntity(dto, true);
    }

    public static Course toEntity(CourseDTO dto, boolean includeAssociations) {
        if (dto == null) return null;

        Course course = new Course();
        course.setId(dto.getId());
        course.setName(dto.getName());
        course.setCode(dto.getCode());
        course.setShortname(dto.getShortname());
        course.setDescription(dto.getDescription());
        course.setCreatedAt(dto.getCreatedAt());
        course.setUpdatedAt(dto.getUpdatedAt());

        if (dto.getInstituteId() != null) {
            Institute institute = new Institute();
            institute.setId(dto.getInstituteId());
            course.setInstitute(institute);
        }

        if (includeAssociations && dto.getBranches() != null) {
            List<Branch> branches = dto.getBranches().stream()
                    .map(branchDTO -> BranchMapper.toEntity(branchDTO, course, true)) // new signature expected
                    .collect(Collectors.toList());
            course.setBranches(branches);
        }

        return course;
    }
}
