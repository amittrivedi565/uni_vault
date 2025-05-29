package com.microservice.universitycontentservice.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name="Institute")
public class instituteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @NotBlank(message = "University name is required")
    @Size(min=3,max=100)
    private String name;

    @Size(min=3,max=100)
    @NotBlank(message = "University shortname is required")
    private String shortname;

    @Size(min=3,max=20)
    @NotBlank(message = "University code is required")
    private String code;

    @Size(min=3,max=100)
    @NotBlank(message = "University description is required")
    private String description;

    @OneToMany(mappedBy = "institute", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<courseEntity> courses;

}
