package com.univault.ims.service;

import com.univault.ims.dto.InstituteDTO;

import java.util.List;
import java.util.UUID;

public interface InstituteService {
    List<InstituteDTO> getAllInstitutes();
    InstituteDTO getInstituteById(UUID id);
    InstituteDTO createInstitute(InstituteDTO dto);
    void deleteInstitute(UUID instituteId);
    InstituteDTO updateInstitute(UUID instituteId, InstituteDTO updatedInstituteData);
}
