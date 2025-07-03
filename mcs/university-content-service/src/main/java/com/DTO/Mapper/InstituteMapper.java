package com.DTO.Mapper;

import com.DTO.InstituteDTO;
import com.Entity.Course;
import com.Entity.Institute;

import java.util.List;
import java.util.stream.Collectors;

public class InstituteMapper {

    public static InstituteDTO toDTO(Institute institute) {
        return toDTO(institute, false); // default: no associations
    }

    public static InstituteDTO toDTO(Institute institute, boolean includeAssociations) {
        if (institute == null) return null;

        InstituteDTO dto = new InstituteDTO();
        dto.setId(institute.getId());
        dto.setName(institute.getName());
        dto.setShortname(institute.getShortname());
        dto.setCode(institute.getCode());
        dto.setDescription(institute.getDescription());
        dto.setCreatedAt(institute.getCreatedAt());
        dto.setUpdatedAt(institute.getUpdatedAt());

        if (includeAssociations && institute.getCourses() != null) {
            dto.setCourses(
                    institute.getCourses().stream()
                            .map(course -> CourseMapper.toDTO(course, false)) // optionally pass includeAssociations
                            .collect(Collectors.toList())
            );
        } else {
            dto.setCourses(null); // or omit if DTO uses `@JsonInclude(Include.NON_NULL)`
        }

        return dto;
    }

    public static Institute toEntity(InstituteDTO dto) {
        return toEntity(dto, false);
    }

    public static Institute toEntity(InstituteDTO dto, boolean includeAssociations) {
        if (dto == null) return null;

        Institute institute = new Institute();
        institute.setId(dto.getId());
        institute.setName(dto.getName());
        institute.setShortname(dto.getShortname());
        institute.setCode(dto.getCode());
        institute.setDescription(dto.getDescription());
        institute.setCreatedAt(dto.getCreatedAt());
        institute.setUpdatedAt(dto.getUpdatedAt());

        if (includeAssociations && dto.getCourses() != null) {
            List<Course> courses = dto.getCourses().stream()
                    .map(course -> {
                        Course entity = CourseMapper.toEntity(course, false); // optionally control depth
                        entity.setInstitute(institute);
                        return entity;
                    })
                    .collect(Collectors.toList());
            institute.setCourses(courses);
        }

        return institute;
    }
}
