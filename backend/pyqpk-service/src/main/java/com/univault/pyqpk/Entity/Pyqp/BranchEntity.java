package com.univault.pyqpk.Entity.Pyqp;

import jakarta.persistence.*;
import java.util.*;
import lombok.Data;

@Entity
@Data
public class BranchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @ManyToOne
    private CourseEntity course;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private List<SemesterEntity> semesters = new ArrayList<>();
}
