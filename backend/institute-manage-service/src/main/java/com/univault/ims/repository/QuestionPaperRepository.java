package com.univault.ims.repository;

import com.univault.ims.entity.QuestionPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionPaperRepository extends JpaRepository<QuestionPaper, UUID> {
    QuestionPaper findBySubjectName(String subjectName);
    List<QuestionPaper> findBySubjectId(UUID subjectId);
}
