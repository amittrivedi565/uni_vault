package com.univault.ims.service;

import com.univault.ims.dto.SubjectDTO;

import java.util.List;
import java.util.UUID;

public interface SubjectService {
    SubjectDTO getSubjectById(UUID id);
    List<SubjectDTO> getAllSubjectsBySemesterId(UUID semesterId);
    SubjectDTO createSubject(SubjectDTO subjectDTO);
    void deleteSubject(UUID subjectId);
    SubjectDTO updateSubject(UUID subjectId, SubjectDTO updatedSubjectData);
    List<SubjectDTO> getSubjects();
}
