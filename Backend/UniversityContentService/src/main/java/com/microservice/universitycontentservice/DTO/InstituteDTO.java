package com.microservice.universitycontentservice.DTO;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class InstituteDTO {
    private UUID id;
    private String name;
    private String shortname;
    private String code;
    private String description;
    private List<CourseDTO> courses;
}