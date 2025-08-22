package com.univault.ims.dto;

import jakarta.validation.Valid;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class InstituteDTO {
    private UUID id;

    @NotBlank(message = "Institute name is required")
    @Size(min = 3, max = 100, message = "Institute name must be between 3 and 30 characters")
    private String name;

    @NotBlank(message = "Institute shortname is required")
    @Size(min = 2, max = 10, message = "Institute shortname must be between 2 and 10 characters")
    private String shortname;

    @Size(min = 3, max = 10, message = "Institute code must be between 3 and 10 characters")
    private String code;

    @Size(min = 0, max = 100, message = "Institute description must be between 0 and 100 characters")
    private String description;

    @Valid
    private List<CourseDTO> courses;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
