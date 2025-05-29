package com.service.src.Dto.Mapper;

import com.service.src.Dto.Response.branchResponseDto;
import com.service.src.Entity.branchEntity;

public class branchMapper {
    public static branchResponseDto toDto(branchEntity entity) {
        branchResponseDto dto = new branchResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        dto.setDescription(entity.getDescription());
        return dto;
    }
}
