package com.univault_ucs.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name="Institute")
public class Institute {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    private String name;

    private String shortname;

    private String code;

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

    @OneToMany(mappedBy = "institute", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Course> courses;

}
