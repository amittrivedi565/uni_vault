package com.service.src.Dto.Mapper;

import com.service.src.Dto.Response.courseResponseDto;
import com.service.src.Dto.Response.instituteResponseDto;
import com.service.src.Entity.instituteEntity;

import java.util.List;
import java.util.stream.Collectors;

public class instituteMapper {

    public static instituteResponseDto toDto(instituteEntity entity) {
        instituteResponseDto dto = new instituteResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setShortname(entity.getShortname());
        dto.setCode(entity.getCode());
        dto.setDescription(entity.getDescription());

        if (entity.getCourses() != null) {
            List<courseResponseDto> courseDto = entity.getCourses().stream()
                    .map(courseMapper::toDto)
                    .collect(Collectors.toList());
            dto.setCourses(courseDto);
        }
        return dto;
    }
}