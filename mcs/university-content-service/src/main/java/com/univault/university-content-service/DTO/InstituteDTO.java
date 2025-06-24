package com.DTO;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

@Data
public class InstituteDTO {
    private UUID id;

    @NotBlank(message = "Institute name is required")
    @Size(min = 3, max = 100, message = "Institute name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Institute shortname is required")
    @Size(min = 3, max = 100, message = "Institute shortname must be between 3 and 100 characters")
    private String shortname;

    @NotBlank(message = "Institute code is required")
    @Size(min = 3, max = 20, message = "Institute code must be between 3 and 20 characters")
    private String code;

    @NotBlank(message = "Institute description is required")
    @Size(min = 3, max = 100, message = "Institute description must be between 3 and 100 characters")
    private String description;

    private List<CourseDTO> courses;
}
