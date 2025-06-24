package com.DTO.Mapper;

import com.DTO.BranchDTO;
import com.Entity.Branch;
import com.Entity.Course;
import com.Entity.Semester;

import java.util.List;
import java.util.stream.Collectors;

public class BranchMapper {

    public static BranchDTO toDTO(Branch branch) {
        if (branch == null) return null;

        BranchDTO dto = new BranchDTO();
        dto.setId(branch.getId());
        dto.setName(branch.getName());
        dto.setShortname(branch.getShortname());
        dto.setCode(branch.getCode());
        dto.setDescription(branch.getDescription());

        if (branch.getCourse() != null) {
            dto.setCourseId(branch.getCourse().getId());
        }

        dto.setSemesters(
                branch.getSemesters() == null ? null :
                        branch.getSemesters().stream()
                                .map(SemesterMapper::toDTO)
                                .collect(Collectors.toList())
        );

        return dto;
    }

    public static Branch toEntity(BranchDTO dto, Course course) {
        if (dto == null) return null;

        Branch branch = new Branch();
        branch.setId(dto.getId());
        branch.setName(dto.getName());
        branch.setShortname(dto.getShortname());
        branch.setCode(dto.getCode());
        branch.setDescription(dto.getDescription());
        branch.setCourse(course);

        if (dto.getSemesters() != null) {
            List<Semester> semesters = dto.getSemesters().stream()
                    .map(SemesterMapper::toEntity)
                    .peek(semester -> semester.setBranch(branch)) // set back-reference
                    .collect(Collectors.toList());
            branch.setSemesters(semesters);

        }

        return branch;
    }
}
