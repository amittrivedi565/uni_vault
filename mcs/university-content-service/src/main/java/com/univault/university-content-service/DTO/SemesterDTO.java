package com.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class SemesterDTO {
    private UUID id;

    @NotBlank(message = "Semester name is required")
    @Size(min = 3, max = 100, message = "Semester name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Semester code is required")
    @Size(min = 2, max = 20, message = "Semester code must be between 2 and 20 characters")
    private String code;

    @NotBlank(message = "Syllabus is required")
    @Size(min = 3, max = 500, message = "Syllabus must be between 3 and 500 characters")
    private String syllabus;

    @NotNull(message = "Branch ID is required")
    private UUID branchId;

    private List<SubjectDTO> subjects;
}
