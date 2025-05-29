package com.microservice.universitycontentservice.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class BranchDTO {
    private UUID id;
    private String name;
    private String code;
    private String description;
}