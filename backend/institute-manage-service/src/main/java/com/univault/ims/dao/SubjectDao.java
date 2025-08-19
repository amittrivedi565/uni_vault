package com.univault.ims.dao;

import com.univault.ims.entity.Subject;
import com.univault.ims.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class SubjectDao {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectDao(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    /*
       Save or update a Subject
    */
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    /*
       Find Subject by ID
    */
    public Optional<Subject> findById(UUID id) {
        return subjectRepository.findById(id);
    }

    /*
       Find Subject by Name and Semester ID
    */
    public Optional<Subject> findByNameAndSemesterId(String name, UUID semesterId) {
        return subjectRepository.findByNameAndSemesterId(name, semesterId);
    }

    /*
       Get all Subjects by Semester ID
    */
    public List<Subject> findAllSubjectsBySemesterId(UUID semesterId) {
        return subjectRepository.findAllSubjectsBySemesterId(semesterId);
    }

    /*
       Get all Subjects
    */
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    /*
       Delete a Subject
    */
    public void delete(Subject subject) {
        subjectRepository.delete(subject);
    }
}
