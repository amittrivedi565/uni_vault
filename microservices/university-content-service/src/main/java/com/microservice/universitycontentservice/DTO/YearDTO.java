package com.microservice.universitycontentservice.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class YearDTO {
    private UUID id;

    @NotBlank(message = "Year name is required")
    @Size(min = 2, max = 100, message = "Year name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Year code is required")
    @Size(min = 1, max = 20, message = "Year code must be between 1 and 20 characters")
    private String code;

    @NotNull(message = "Branch ID is required")
    private UUID branchId;

    @Valid
    private List<SemesterDTO> semesters;
}
