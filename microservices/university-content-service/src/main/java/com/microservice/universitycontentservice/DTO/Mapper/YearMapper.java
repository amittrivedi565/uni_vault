package com.microservice.universitycontentservice.DTO.Mapper;

import com.microservice.universitycontentservice.DTO.SemesterDTO;
import com.microservice.universitycontentservice.DTO.YearDTO;
import com.microservice.universitycontentservice.Entity.Branch;
import com.microservice.universitycontentservice.Entity.Semester;
import com.microservice.universitycontentservice.Entity.Year;

import java.util.List;
import java.util.stream.Collectors;

public class YearMapper {

    public static YearDTO toDTO(Year year) {
        if (year == null) return null;

        YearDTO dto = new YearDTO();
        dto.setId(year.getId());
        dto.setName(year.getName());
        dto.setCode(year.getCode());

        if (year.getBranch() != null) {
            dto.setBranchId(year.getBranch().getId());
        }

        dto.setSemesters(
                year.getSemesters() == null ? null :
                        year.getSemesters().stream()
                                .map(SemesterMapper::toDTO)
                                .collect(Collectors.toList())
        );

        return dto;
    }

    public static Year toEntity(YearDTO dto) {
        if (dto == null) return null;

        Year year = new Year();
        year.setId(dto.getId());
        year.setName(dto.getName());
        year.setCode(dto.getCode());

        if (dto.getBranchId() != null) {
            Branch branch = new Branch();
            branch.setId(dto.getBranchId());
            year.setBranch(branch);
        }

        if (dto.getSemesters() != null) {
            List<Semester> semesters = dto.getSemesters().stream()
                    .map(SemesterMapper::toEntity)
                    .peek(semester -> semester.setYear(year)) // set back-reference
                    .collect(Collectors.toList());
            year.setSemesters(semesters);
        }

        return year;
    }
}
