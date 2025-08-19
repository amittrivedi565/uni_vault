package com.univault.ims.dao;

import com.univault.ims.entity.Institute;
import com.univault.ims.repository.InstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class InstituteDao {

    private final InstituteRepository instituteRepository;

    @Autowired
    public InstituteDao(InstituteRepository instituteRepository) {
        this.instituteRepository = instituteRepository;
    }

    /*
       Save or update an Institute
    */
    public Institute save(Institute institute) {
        return instituteRepository.save(institute);
    }

    /*
       Find an Institute by UUID
    */
    public Optional<Institute> findById(UUID id) {
        return instituteRepository.findById(id);
    }

    /*
       Find an Institute by name
    */
    public Optional<Institute> findByName(String name) {
        return instituteRepository.findByName(name);
    }

    /*
       Get all Institutes
    */
    public List<Institute> findAll() {
        return instituteRepository.findAll();
    }

    /*
       Delete an Institute
    */
    public void delete(Institute institute) {
        instituteRepository.delete(institute);
    }
}
