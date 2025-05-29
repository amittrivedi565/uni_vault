package com.microservice.universitycontentservice.Dto.Response;

import lombok.Data;

import java.util.UUID;

@Data
public class branchResponseDto {
    private UUID id;
    private String name;
    private String code;
    private String description;
}