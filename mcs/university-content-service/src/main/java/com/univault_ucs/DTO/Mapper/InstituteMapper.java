package com.univault_ucs.DTO.Mapper;

import com.univault_ucs.DTO.InstituteDTO;
import com.univault_ucs.DTO.CourseDTO;
import com.univault_ucs.Entity.Course;
import com.univault_ucs.Entity.Institute;

import java.util.List;
import java.util.stream.Collectors;

public class InstituteMapper {

    public static InstituteDTO toDTO(Institute institute) {
        return toDTO(institute, false);
    }

    public static InstituteDTO toDTO(Institute institute, boolean includeAssociations) {
        if (institute == null) return null;

        InstituteDTO dto = basicDTO(institute);

        if (includeAssociations) {
            dto.setCourses(mapCoursesToDTO(institute.getCourses()));
        }

        return dto;
    }

    public static Institute toEntity(InstituteDTO dto) {
        return toEntity(dto, false);
    }

    public static Institute toEntity(InstituteDTO dto, boolean includeAssociations) {
        if (dto == null) return null;

        Institute institute = basicEntity(dto);

        if (includeAssociations) {
            institute.setCourses(mapCoursesToEntity(dto.getCourses(), institute));
        }

        return institute;
    }

    // ----- Private Helper Methods -----

    private static InstituteDTO basicDTO(Institute institute) {
        InstituteDTO dto = new InstituteDTO();
        dto.setId(institute.getId());
        dto.setName(institute.getName());
        dto.setShortname(institute.getShortname());
        dto.setCode(institute.getCode());
        dto.setDescription(institute.getDescription());
        dto.setCreatedAt(institute.getCreatedAt());
        dto.setUpdatedAt(institute.getUpdatedAt());
        return dto;
    }

    private static Institute basicEntity(InstituteDTO dto) {
        Institute institute = new Institute();
        institute.setId(dto.getId());
        institute.setName(dto.getName());
        institute.setShortname(dto.getShortname());
        institute.setCode(dto.getCode());
        institute.setDescription(dto.getDescription());
        institute.setCreatedAt(dto.getCreatedAt());
        institute.setUpdatedAt(dto.getUpdatedAt());
        return institute;
    }

    private static List<CourseDTO> mapCoursesToDTO(List<Course> courses) {
        if (courses == null) return null;

        return courses.stream()
                .map(course -> CourseMapper.toDTO(course, false)) // control depth if needed
                .collect(Collectors.toList());
    }

    private static List<Course> mapCoursesToEntity(List<CourseDTO> courseDTOs, Institute institute) {
        if (courseDTOs == null) return null;

        return courseDTOs.stream()
                .map(courseDTO -> {
                    Course course = CourseMapper.toEntity(courseDTO, false);
                    course.setInstitute(institute);
                    return course;
                })
                .collect(Collectors.toList());
    }
}
