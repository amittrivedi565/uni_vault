package com.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name="Unit")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Unit name is required")
    @Size(min=3,max=100)
    private String name;

    @Size(min=3,max=100)
    @NotBlank(message = "Unit shortname is required")
    private String shortname;

    @Size(min=3,max=20)
    @NotBlank(message = "Unit code is required")
    private String code;

    @Size(min=3,max=100)
    @NotBlank(message = "Unit description is required")
    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    private UUID resourceId;

}
