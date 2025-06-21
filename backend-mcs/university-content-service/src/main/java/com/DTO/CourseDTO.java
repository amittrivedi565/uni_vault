package com.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CourseDTO {
    private UUID id;

    @NotBlank(message = "Course name is required")
    @Size(min = 3, max = 100, message = "Course name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Course shortname is required")
    @Size(min = 3, max = 100, message = "Branch name must be between 3 and 100 characters")
    private String shortname;

    @NotBlank(message = "Course code is required")
    @Size(min = 2, max = 20, message = "Course code must be between 2 and 20 characters")
    private String code;

    @NotBlank(message = "Course description is required")
    @Size(min = 3, max = 200, message = "Course description must be between 3 and 200 characters")
    private String description;

    private UUID instituteId;

    private List<BranchDTO> branches;
}
