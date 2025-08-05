package com.univault.pyqpk.Entity.Pyqp;

import jakarta.persistence.*;
import java.util.*;
import lombok.Data;

@Entity
@Data
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @ManyToOne
    private InstituteEntity institute;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<BranchEntity> branches = new ArrayList<>();
}
