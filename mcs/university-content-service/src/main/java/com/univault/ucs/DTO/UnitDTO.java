package com.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class UnitDTO {
    private UUID id;

    @NotBlank(message = "Unit name is required")
    @Size(min = 3, max = 100, message = "Unit name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Unit shortname is required")
    @Size(min = 2, max = 50, message = "Unit shortname must be between 2 and 50 characters")
    private String shortname;

    @NotBlank(message = "Unit code is required")
    @Size(min = 2, max = 20, message = "Unit code must be between 2 and 20 characters")
    private String code;

    @NotBlank(message = "Unit description is required")
    @Size(min = 3, max = 500, message = "Unit description must be between 3 and 500 characters")
    private String description;

    @NotNull(message = "Subject ID is required")
    private UUID subjectId;

    private UUID resourceId;
}
