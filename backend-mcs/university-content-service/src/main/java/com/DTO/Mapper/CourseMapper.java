package com.DTO.Mapper;

import com.DTO.CourseDTO;
import com.Entity.Branch;
import com.Entity.Course;
import com.Entity.Institute;

import java.util.List;
import java.util.stream.Collectors;

public class CourseMapper {

    public static CourseDTO toDTO(Course course) {
        if (course == null) return null;

        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setShortname(course.getShortname());
        dto.setCode(course.getCode());
        dto.setDescription(course.getDescription());

        if (course.getInstitute() != null) {
            dto.setInstituteId(course.getInstitute().getId());
        }

        dto.setBranches(
                course.getBranches() == null ? null :
                        course.getBranches().stream()
                                .map(BranchMapper::toDTO)
                                .collect(Collectors.toList())
        );

        return dto;
    }

    public static Course toEntity(CourseDTO dto) {
        if (dto == null) return null;

        Course course = new Course();
        course.setId(dto.getId());
        course.setName(dto.getName());
        course.setCode(dto.getCode());
        course.setShortname(dto.getShortname());
        course.setDescription(dto.getDescription());

        if (dto.getInstituteId() != null) {
            Institute institute = new Institute();
            institute.setId(dto.getInstituteId());
            course.setInstitute(institute);
        }

        if (dto.getBranches() != null) {
            List<Branch> branches = dto.getBranches().stream()
                    .map(branchDTO -> BranchMapper.toEntity(branchDTO, course)) // pass course for back-reference
                    .collect(Collectors.toList());
            course.setBranches(branches);
        }

        return course;
    }
}
