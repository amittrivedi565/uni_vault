package com.microservice.universitycontentservice.DTO.Mapper;

import com.microservice.universitycontentservice.DTO.BranchDTO;
import com.microservice.universitycontentservice.DTO.YearDTO;
import com.microservice.universitycontentservice.Entity.Branch;
import com.microservice.universitycontentservice.Entity.Course;
import com.microservice.universitycontentservice.Entity.Year;

import java.util.List;
import java.util.stream.Collectors;

public class BranchMapper {

    public static BranchDTO toDTO(Branch branch) {
        if (branch == null) return null;

        BranchDTO dto = new BranchDTO();
        dto.setId(branch.getId());
        dto.setName(branch.getName());
        dto.setCode(branch.getCode());
        dto.setDescription(branch.getDescription());

        if (branch.getCourse() != null) {
            dto.setCourseId(branch.getCourse().getId());
        }

        dto.setYears(
                branch.getYears() == null ? null :
                        branch.getYears().stream()
                                .map(YearMapper::toDTO)
                                .collect(Collectors.toList())
        );

        return dto;
    }

    public static Branch toEntity(BranchDTO dto, Course course) {
        if (dto == null) return null;

        Branch branch = new Branch();
        branch.setId(dto.getId());
        branch.setName(dto.getName());
        branch.setCode(dto.getCode());
        branch.setDescription(dto.getDescription());
        branch.setCourse(course);

        if (dto.getYears() != null) {
            List<Year> years = dto.getYears().stream()
                    .map(YearMapper::toEntity)
                    .peek(year -> year.setBranch(branch)) // set back-reference
                    .collect(Collectors.toList());

            branch.setYears(years);
        }

        return branch;
    }
}
