package com.univault_ucs.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class BranchDTO {
    private UUID id;

    @NotBlank(message = "Branch name is required")
    @Size(min = 3, max = 20, message = "Branch name must be between 3 and 20 characters")
    private String name;

    @NotBlank(message = "Branch shortname is required")
    @Size(min = 1, max = 10, message = "Branch name must be between 1 and 10 characters")
    private String shortname;

    @Size(min = 1, max = 20, message = "Branch code must be between 1 and 20 characters")
    private String code;

    @Size(min = 0, max = 100, message = "Branch description must be between 0 and 100 characters")
    private String description;

    private UUID courseId;

    @Valid
    private List<SemesterDTO> semesters;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
