package com.microservice.universitycontentservice.Dto.Mapper;
import com.microservice.universitycontentservice.Dto.CourseDTO;
import com.microservice.universitycontentservice.Dto.InstituteDTO;
import com.microservice.universitycontentservice.Entity.Institute;

import java.util.List;
import java.util.stream.Collectors;

public class InstituteMapper {

    public static InstituteDTO toDto(Institute entity) {
        InstituteDTO dto = new InstituteDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setShortname(entity.getShortname());
        dto.setCode(entity.getCode());
        dto.setDescription(entity.getDescription());

        if (entity.getCourses() != null) {
            List<CourseDTO> courseDto = entity.getCourses().stream()
                    .map(CourseMapper::toDto)
                    .collect(Collectors.toList());
            dto.setCourses(courseDto);
        }
        return dto;
    }
}