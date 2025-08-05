package com.univault.pyqpk.Entity.Pyqp;

import jakarta.persistence.*;
import java.util.*;
import lombok.Data;

@Entity
@Data
public class QuestionPaperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Integer year;
    private String session;
    private UUID resourceId;

    @ManyToOne
    private SubjectEntity subject;
}
