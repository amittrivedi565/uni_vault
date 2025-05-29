package com.microservice.universitycontentservice.Dto.Mapper;

import com.microservice.universitycontentservice.Dto.Response.branchResponseDto;
import com.microservice.universitycontentservice.Entity.branchEntity;

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
