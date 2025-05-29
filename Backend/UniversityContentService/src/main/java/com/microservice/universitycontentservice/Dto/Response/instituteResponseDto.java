package com.microservice.universitycontentservice.Dto.Response;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class instituteResponseDto {
    private UUID id;
    private String name;
    private String shortname;
    private String code;
    private String description;
    private List<courseResponseDto> courses;
}