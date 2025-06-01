package com.microservice.universitycontentservice.DTO.Mapper;
import com.microservice.universitycontentservice.DTO.CourseDTO;
import com.microservice.universitycontentservice.Entity.Branch;
import com.microservice.universitycontentservice.Entity.Course;
import com.microservice.universitycontentservice.Entity.Institute;

import java.util.List;
import java.util.stream.Collectors;

public class CourseMapper {
    public static CourseDTO toDTO(Course request) {

        if (request == null) return null;

        CourseDTO dto = new CourseDTO();

        dto.setId(request.getId());
        dto.setName(request.getName());
        dto.setCode(request.getCode());
        dto.setDescription(request.getDescription());
        dto.setInstituteId(request.getInstitute() != null ? request.getInstitute().getId() : null);
        if (request.getBranches() != null) {
            dto.setBranches(request.getBranches().stream()
                    .map(BranchMapper::toDTO)
                    .toList());
        }
        return dto;
    }
    public static Course toEntity(CourseDTO dto, Institute institute) {
        if (dto == null) return null;

        Course course = new Course();
        course.setId(dto.getId());  // Usually null for new entity, set if updating
        course.setName(dto.getName());
        course.setCode(dto.getCode());
        course.setDescription(dto.getDescription());
        course.setInstitute(institute);

        if (dto.getBranches() != null) {
            List<Branch> branches = dto.getBranches().stream()
                    .map(BranchMapper::toEntity)
                    .collect(Collectors.toList());
            course.setBranches(branches);
        }

        return course;
    }
}
