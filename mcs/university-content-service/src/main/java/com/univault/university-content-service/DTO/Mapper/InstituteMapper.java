package com.DTO.Mapper;

import com.DTO.InstituteDTO;
import com.Entity.Course;
import com.Entity.Institute;

import java.util.List;
import java.util.stream.Collectors;

public class InstituteMapper {

    public static InstituteDTO toDTO(Institute institute) {
        if (institute == null) return null;

        InstituteDTO dto = new InstituteDTO();
        dto.setId(institute.getId());
        dto.setName(institute.getName());
        dto.setShortname(institute.getShortname());
        dto.setCode(institute.getCode());
        dto.setDescription(institute.getDescription());

        dto.setCourses(
                institute.getCourses() == null ? null :
                        institute.getCourses().stream()
                                .map(CourseMapper::toDTO)
                                .collect(Collectors.toList())
        );

        return dto;
    }

    public static Institute toEntity(InstituteDTO dto) {
        if (dto == null) return null;

        Institute institute = new Institute();
        institute.setId(dto.getId());
        institute.setName(dto.getName());
        institute.setShortname(dto.getShortname());
        institute.setCode(dto.getCode());
        institute.setDescription(dto.getDescription());

        if (dto.getCourses() != null) {
            List<Course> courses = dto.getCourses().stream()
                    .map(CourseMapper::toEntity) // Must set back-reference later
                    .peek(course -> course.setInstitute(institute))
                    .collect(Collectors.toList());
            institute.setCourses(courses);
        }

        return institute;
    }
}
