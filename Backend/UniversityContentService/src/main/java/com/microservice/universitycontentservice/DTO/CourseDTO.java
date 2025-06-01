package com.microservice.universitycontentservice.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CourseDTO {
    private UUID id;
    private String name;
    private String code;
    private String description;
    private UUID instituteId;
    private List<BranchDTO> branches;
}