package com.univault.ims.serviceImpl;

import com.univault.ims.entity.QuestionPaper;
import com.univault.ims.entity.Subject;
import com.univault.ims.exception.service.QuestionPaperServiceException;
import com.univault.ims.repository.QuestionPaperRepository;
import com.univault.ims.repository.SubjectRepository;
import com.univault.ims.service.QuestionPaperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QuestionPaperServiceImpl implements QuestionPaperService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionPaperServiceImpl.class);

    private final QuestionPaperRepository questionPaperRepository;
    private final SubjectRepository subjectRepository;

    public QuestionPaperServiceImpl(QuestionPaperRepository questionPaperRepository, SubjectRepository subjectRepository) {
        this.questionPaperRepository = questionPaperRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<QuestionPaper> getAllQuestionPaper(UUID subjectId) {
        try {
            List<QuestionPaper> papers = questionPaperRepository.findBySubjectId(subjectId);
            if (papers.isEmpty()) {
                String message = "No question papers found for Subject ID: " + subjectId;
                logger.warn(message);
                throw new QuestionPaperServiceException(message);
            }
            return papers;
        } catch (Exception e) {
            logger.error("Error in getAllQuestionPaper", e);
            throw new QuestionPaperServiceException("An error occurred while fetching question papers.", e);
        }
    }

    @Override
    public QuestionPaper createQuestionPaper(QuestionPaper request) {
        if (request.getSubject() == null || request.getSubject().getId() == null) {
            String message = "Subject ID is required to create a Question Paper.";
            logger.warn(message);
            throw new QuestionPaperServiceException(message);
        }

        Subject subject = subjectRepository.findById(request.getSubject().getId())
                .orElseThrow(() -> {
                    String message = "Subject not found with ID: " + request.getSubject().getId();
                    logger.warn(message);
                    return new QuestionPaperServiceException(message);
                });

        QuestionPaper existingPaper = questionPaperRepository.findBySubjectName(request.getSubjectName());
        if (existingPaper != null) {
            String message = "Question paper with subject name '" + request.getSubjectName() + "' already exists.";
            logger.warn(message);
            throw new QuestionPaperServiceException(message);
        }

        try {
            request.setSubject(subject);
            QuestionPaper savedPaper = questionPaperRepository.save(request);
            logger.info("Question Paper created successfully with ID: {}", savedPaper.getId());
            return savedPaper;
        } catch (Exception e) {
            logger.error("Error in createQuestionPaper", e);
            throw new QuestionPaperServiceException("An error occurred while creating the question paper.", e);
        }
    }
}
