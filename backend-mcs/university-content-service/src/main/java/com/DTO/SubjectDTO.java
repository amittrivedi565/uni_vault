package com.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class SubjectDTO {
    private UUID id;

    @NotBlank(message = "Subject name is required")
    @Size(min = 3, max = 100, message = "Subject name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Subject shortname is required")
    @Size(min = 2, max = 50, message = "Subject shortname must be between 2 and 50 characters")
    private String shortname;

    @NotBlank(message = "Subject code is required")
    @Size(min = 2, max = 20, message = "Subject code must be between 2 and 20 characters")
    private String code;

    @NotBlank(message = "Subject description is required")
    @Size(min = 3, max = 500, message = "Subject description must be between 3 and 500 characters")
    private String description;

    @NotNull(message = "Semester ID is required")
    private UUID semesterId;

    private List<UnitDTO> units;
}
