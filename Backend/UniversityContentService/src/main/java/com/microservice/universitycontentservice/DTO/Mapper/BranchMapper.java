package com.microservice.universitycontentservice.DTO.Mapper;

import com.microservice.universitycontentservice.DTO.BranchDTO;
import com.microservice.universitycontentservice.Entity.Branch;

public class BranchMapper {
    public static BranchDTO toDTO(Branch entity) {
        BranchDTO dto = new BranchDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        dto.setDescription(entity.getDescription());
        return dto;
    }
    // DTO to Entity
    public static Branch toEntity(BranchDTO dto) {
        if (dto == null) return null;
        Branch entity = new Branch();
        entity.setId(dto.getId());  // usually null when creating new
        entity.setName(dto.getName());
        entity.setCode(dto.getCode());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
