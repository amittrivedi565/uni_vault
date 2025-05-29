package com.service.src.Dto.Mapper;

import com.service.src.Dto.Response.branchResponseDto;
import com.service.src.Dto.Response.courseResponseDto;
import com.service.src.Entity.courseEntity;

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
