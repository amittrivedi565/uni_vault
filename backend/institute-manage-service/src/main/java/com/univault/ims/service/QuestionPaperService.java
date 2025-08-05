package com.univault.ims.service;

import com.univault.ims.entity.QuestionPaper;

import java.util.List;
import java.util.UUID;

public interface QuestionPaperService {
    List<QuestionPaper> getAllQuestionPaper(UUID subjectId);
    QuestionPaper createQuestionPaper(QuestionPaper request);
}
