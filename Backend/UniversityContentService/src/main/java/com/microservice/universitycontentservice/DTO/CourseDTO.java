package com.microservice.universitycontentservice.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Data
public class CourseDTO {

    private UUID id;
    private String name;
    private String code;
    private String description;


    @Setter
    @Getter
    private List<BranchDTO> branches;

}