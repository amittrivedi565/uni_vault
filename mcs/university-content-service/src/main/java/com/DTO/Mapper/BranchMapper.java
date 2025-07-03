package com.DTO.Mapper;

import com.DTO.BranchDTO;
import com.Entity.Branch;
import com.Entity.Course;
import com.Entity.Semester;

import java.util.List;
import java.util.stream.Collectors;

public class BranchMapper {

    public static BranchDTO toDTO(Branch branch) {
        return toDTO(branch, false); // default: don't include semesters
    }

    public static BranchDTO toDTO(Branch branch, boolean includeAssociations) {
        if (branch == null) return null;

        BranchDTO dto = new BranchDTO();
        dto.setId(branch.getId());
        dto.setName(branch.getName());
        dto.setShortname(branch.getShortname());
        dto.setCode(branch.getCode());
        dto.setDescription(branch.getDescription());

        dto.setCreatedAt(branch.getCreatedAt());
        dto.setUpdatedAt(branch.getUpdatedAt());

        if (branch.getCourse() != null) {
            dto.setCourseId(branch.getCourse().getId());
        }

        if (includeAssociations && branch.getSemesters() != null) {
            dto.setSemesters(
                    branch.getSemesters().stream()
                            .map(semester -> SemesterMapper.toDTO(semester, false)) // you can later support nested if needed
                            .collect(Collectors.toList())
            );
        } else {
            dto.setSemesters(null);
        }

        return dto;
    }

    public static Branch toEntity(BranchDTO dto, Course course) {
        return toEntity(dto, course, false); // default: no nested semesters
    }

    public static Branch toEntity(BranchDTO dto, Course course, boolean includeAssociations) {
        if (dto == null) return null;

        Branch branch = new Branch();
        branch.setId(dto.getId());
        branch.setName(dto.getName());
        branch.setShortname(dto.getShortname());
        branch.setCode(dto.getCode());
        branch.setDescription(dto.getDescription());

        branch.setCourse(course);
        branch.setCreatedAt(dto.getCreatedAt());
        branch.setUpdatedAt(dto.getUpdatedAt());

        if (includeAssociations && dto.getSemesters() != null) {
            List<Semester> semesters = dto.getSemesters().stream()
                    .map(semesterDTO -> {
                        Semester semester = SemesterMapper.toEntity(semesterDTO, false); // optionally limit nesting
                        semester.setBranch(branch);
                        return semester;
                    })
                    .collect(Collectors.toList());
            branch.setSemesters(semesters);
        }

        return branch;
    }
}
