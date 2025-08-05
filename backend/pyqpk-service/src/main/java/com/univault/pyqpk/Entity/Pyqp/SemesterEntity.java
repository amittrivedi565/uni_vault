package com.univault.pyqpk.Entity.Pyqp;

import jakarta.persistence.*;
import java.util.*;
import lombok.Data;

@Entity
@Data
public class SemesterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @ManyToOne
    private BranchEntity branch;

    @OneToMany(mappedBy = "semester", cascade = CascadeType.ALL)
    private List<SubjectEntity> subjects = new ArrayList<>();
}
