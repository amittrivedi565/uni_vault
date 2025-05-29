package com.microservice.universitycontentservice.DTO.Mapper;
import com.microservice.universitycontentservice.DTO.BranchDTO;
import com.microservice.universitycontentservice.DTO.CourseDTO;
import com.microservice.universitycontentservice.Entity.Course;

import java.util.List;
import java.util.stream.Collectors;

public class CourseMapper {
    public static CourseDTO toDto(Course entity) {
        CourseDTO dto = new CourseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        dto.setDescription(entity.getDescription());

        if (entity.getBranches() != null) {
            List<BranchDTO> branchDtos = entity.getBranches().stream()
                    .map(BranchMapper::toDto)
                    .collect(Collectors.toList());
            dto.setBranches(branchDtos);
        }
        return dto;
    }
}
