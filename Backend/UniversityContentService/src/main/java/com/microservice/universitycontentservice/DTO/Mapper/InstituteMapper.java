package com.microservice.universitycontentservice.DTO.Mapper;
import com.microservice.universitycontentservice.DTO.CourseDTO;
import com.microservice.universitycontentservice.DTO.InstituteDTO;
import com.microservice.universitycontentservice.Entity.Institute;

import java.util.List;
import java.util.stream.Collectors;

public class InstituteMapper {

    public static InstituteDTO toDTO(Institute request) {
        if (request == null) return null;

        InstituteDTO dto = new InstituteDTO();

        dto.setId(request.getId());
        dto.setName(request.getName());
        dto.setShortname(request.getShortname());
        dto.setCode(request.getCode());
        dto.setDescription(request.getDescription());
        if (request.getCourses() != null) {
            List<CourseDTO> courseDto = request.getCourses().stream()
                    .map(CourseMapper::toDTO)
                    .collect(Collectors.toList());
            dto.setCourses(courseDto);
        }
        return dto;
    }
}