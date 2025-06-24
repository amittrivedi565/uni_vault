package com.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class BranchDTO {
    private UUID id;

    @NotBlank(message = "Branch name is required")
    @Size(min = 3, max = 100, message = "Branch name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Branch shortname is required")
    @Size(min = 3, max = 100, message = "Branch name must be between 3 and 100 characters")
    private String shortname;

    @NotBlank(message = "Branch code is required")
    @Size(min = 2, max = 20, message = "Branch code must be between 2 and 20 characters")
    private String code;

    @NotBlank(message = "Branch description is required")
    @Size(min = 3, max = 200, message = "Branch description must be between 3 and 200 characters")
    private String description;

    private UUID courseId;

    private List<SemesterDTO> semesters;
}
