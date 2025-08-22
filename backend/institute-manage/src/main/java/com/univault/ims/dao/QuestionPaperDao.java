package com.univault.ims.dao;

import com.univault.ims.entity.QuestionPaper;
import com.univault.ims.repository.QuestionPaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class QuestionPaperDao {

    private final QuestionPaperRepository questionPaperRepository;

    @Autowired
    public QuestionPaperDao(QuestionPaperRepository questionPaperRepository) {
        this.questionPaperRepository = questionPaperRepository;
    }

    /*
       Save or update a QuestionPaper
    */
    public QuestionPaper save(QuestionPaper questionPaper) {
        return questionPaperRepository.save(questionPaper);
    }

    /*
       Find QuestionPaper by ID
    */
    public Optional<QuestionPaper> findById(UUID id) {
        return questionPaperRepository.findById(id);
    }

    /*
       Find QuestionPaper by Subject Name
    */
    public QuestionPaper findBySubjectName(String subjectName) {
        return questionPaperRepository.findBySubjectName(subjectName);
    }

    /*
       Get all QuestionPapers for a Subject
    */
    public List<QuestionPaper> findBySubjectId(UUID subjectId) {
        return questionPaperRepository.findBySubjectId(subjectId);
    }

    /*
       Delete a QuestionPaper
    */
    public void delete(QuestionPaper questionPaper) {
        questionPaperRepository.delete(questionPaper);
    }
}
