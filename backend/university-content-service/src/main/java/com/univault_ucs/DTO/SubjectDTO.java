package com.univault_ucs.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class SubjectDTO {
    private UUID id;

    @NotBlank(message = "Subject name is required")
    @Size(min = 3, max = 20, message = "Subject name must be between 3 and 20 characters")
    private String name;

    @NotBlank(message = "Subject shortname is required")
    @Size(min = 2, max = 10, message = "Subject shortname must be between 2 and 10 characters")
    private String shortname;

    @NotBlank(message = "Subject code is required")
    @Size(min = 2, max = 10, message = "Subject code must be between 2 and 10 characters")
    private String code;

    @Size(min = 0, max = 100, message = "Subject description must be between 0 and 100 characters")
    private String description;

    @NotNull(message = "Semester ID is required")
    private UUID semesterId;

    @Valid
    private List<UnitDTO> units;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
