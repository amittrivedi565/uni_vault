package com.univault_ucs.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class CourseDTO {
    private UUID id;

    @NotBlank(message = "Course name is required")
    @Size(min = 3, max = 20, message = "Course name must be between 3 and 20 characters")
    private String name;

    @NotBlank(message = "Course shortname is required")
    @Size(min = 2, max = 10, message = "Branch name must be between 2 and 10 characters")
    private String shortname;

    @NotBlank(message = "Course code is required")
    @Size(min = 2, max = 20, message = "Course code must be between 2 and 20 characters")
    private String code;

    @Size(min = 0, max = 100, message = "Course description must be between 0 and 100 characters")
    private String description;

    private UUID instituteId;

    @Valid
    private List<BranchDTO> branches;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
