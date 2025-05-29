package com.microservice.universitycontentservice.Dto.Mapper;

import com.microservice.universitycontentservice.Dto.BranchDTO;
import com.microservice.universitycontentservice.Entity.Branch;

public class BranchMapper {
    public static BranchDTO toDto(Branch entity) {
        BranchDTO dto = new BranchDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        dto.setDescription(entity.getDescription());
        return dto;
    }
}
