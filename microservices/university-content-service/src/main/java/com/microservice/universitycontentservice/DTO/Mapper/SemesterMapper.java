package com.microservice.universitycontentservice.DTO.Mapper;

import com.microservice.universitycontentservice.DTO.SemesterDTO;
import com.microservice.universitycontentservice.DTO.SubjectDTO;
import com.microservice.universitycontentservice.Entity.Semester;
import com.microservice.universitycontentservice.Entity.Subject;
import com.microservice.universitycontentservice.Entity.Year;

import java.util.List;
import java.util.stream.Collectors;

public class SemesterMapper {

    public static SemesterDTO toDTO(Semester semester) {
        if (semester == null) return null;

        SemesterDTO dto = new SemesterDTO();
        dto.setId(semester.getId());
        dto.setName(semester.getName());
        dto.setCode(semester.getCode());
        dto.setSyllabus(semester.getSyllabus());

        if (semester.getYear() != null) {
            dto.setYearId(semester.getYear().getId());
        }

        dto.setSubjects(
                semester.getSubjects() == null ? null :
                        semester.getSubjects().stream()
                                .map(SubjectMapper::toDTO)
                                .collect(Collectors.toList())
        );

        return dto;
    }

    public static Semester toEntity(SemesterDTO dto) {
        if (dto == null) return null;

        Semester semester = new Semester();
        semester.setId(dto.getId());
        semester.setName(dto.getName());
        semester.setCode(dto.getCode());
        semester.setSyllabus(dto.getSyllabus());

        if (dto.getYearId() != null) {
            Year year = new Year();
            year.setId(dto.getYearId());
            semester.setYear(year);
        }

        if (dto.getSubjects() != null) {
            List<Subject> subjects = dto.getSubjects().stream()
                    .map(SubjectMapper::toEntity)
                    .peek(subject -> subject.setSemester(semester)) // set back-reference
                    .collect(Collectors.toList());
            semester.setSubjects(subjects);
        }

        return semester;
    }
}
