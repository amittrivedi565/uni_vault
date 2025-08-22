package com.univault.ims.service;

import com.univault.ims.dto.SemesterDTO;

import java.util.List;
import java.util.UUID;

public interface SemesterService {
    SemesterDTO getSemesterById(UUID id);
    List<SemesterDTO> getSemestersByBranchId(UUID branchId);
    SemesterDTO createSemester(SemesterDTO semesterDTO);
    void deleteSemester(UUID semesterId);
    SemesterDTO updateSemester(UUID semesterId, SemesterDTO updatedSemesterData);
}
