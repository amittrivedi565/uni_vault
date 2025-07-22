package com.univault_ucs.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UnitDTO {
    private UUID id;

    @NotBlank(message = "Unit name is required")
    @Size(min = 3, max = 20, message = "Unit name must be between 3 and 20 characters")
    private String name;

    @NotBlank(message = "Unit shortname is required")
    @Size(min = 2, max = 10, message = "Unit shortname must be between 2 and 10 characters")
    private String shortname;

    @NotBlank(message = "Unit code is required")
    @Size(min = 2, max = 10, message = "Unit code must be between 2 and 10 characters")
    private String code;

    @Size(min = 0, max = 100, message = "Unit description must be between 0 and 100 characters")
    private String description;

    private UUID resource_id;

    @NotNull(message = "Subject ID is required")
    private UUID subjectId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
