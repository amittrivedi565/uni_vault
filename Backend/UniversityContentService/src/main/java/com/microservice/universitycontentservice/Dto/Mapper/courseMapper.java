package com.microservice.universitycontentservice.Dto.Mapper;
import com.microservice.universitycontentservice.Dto.Response.branchResponseDto;
import com.microservice.universitycontentservice.Dto.Response.courseResponseDto;
import com.microservice.universitycontentservice.Entity.courseEntity;

import java.util.List;
import java.util.stream.Collectors;

public class courseMapper {
    public static courseResponseDto toDto(courseEntity entity) {
        courseResponseDto dto = new courseResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        dto.setDescription(entity.getDescription());

        if (entity.getBranches() != null) {
            List<branchResponseDto> branchDtos = entity.getBranches().stream()
                    .map(branchMapper::toDto)
                    .collect(Collectors.toList());
            dto.setBranches(branchDtos);
        }
        return dto;
    }
}
