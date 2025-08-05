package com.univault.pyqpk.Entity.Pyqp;

import jakarta.persistence.*;
import java.util.*;
import lombok.Data;

@Entity
@Data
public class SubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String code;

    @ManyToOne
    private SemesterEntity semester;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<QuestionPaperEntity> questionPapers = new ArrayList<>();
}
