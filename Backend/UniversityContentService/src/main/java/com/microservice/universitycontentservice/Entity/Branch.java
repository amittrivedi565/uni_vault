package com.microservice.universitycontentservice.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name="Branch")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Branch name is required")
    @Size(min=3,max=100)
    private String name;

    @Size(min=3,max=100)
    @NotBlank(message = "Branch shortname is required")
    private String shortname;

    @Size(min=3,max=20)
    @NotBlank(message = "Branch code is required")
    private String code;

    @Size(min=3,max=100)
    @NotBlank(message = "Branch description is required")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Year> years;
}
