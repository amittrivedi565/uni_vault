package com.univault.ims.controller;

import com.univault.ims.entity.QuestionPaper;
import com.univault.ims.exception.service.QuestionPaperServiceException;
import com.univault.ims.service.QuestionPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/question-papers")
public class QuestionPaperController {

    private final QuestionPaperService questionPaperService;

    @Autowired
    public QuestionPaperController(QuestionPaperService questionPaperService){
        this.questionPaperService = questionPaperService;
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<QuestionPaper>> getAllQuestionPapersBySubject(@PathVariable UUID subjectId) {
        try {
            List<QuestionPaper> response = questionPaperService.getAllQuestionPaper(subjectId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            throw new QuestionPaperServiceException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<QuestionPaper> createQuestionPaperController(@RequestBody QuestionPaper request){
        try{
            QuestionPaper response = questionPaperService.createQuestionPaper(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
