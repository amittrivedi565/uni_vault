package com.univault.ims.dao;

import com.univault.ims.entity.Semester;
import com.univault.ims.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class SemesterDao {

    private final SemesterRepository semesterRepository;

    @Autowired
    public SemesterDao(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    /*
       Save or update a Semester
    */
    public Semester save(Semester semester) {
        return semesterRepository.save(semester);
    }

    /*
       Find Semester by ID
    */
    public Optional<Semester> findById(UUID id) {
        return semesterRepository.findById(id);
    }

    /*
       Find Semester by Name and Branch ID
    */
    public Optional<Semester> findByNameAndBranchId(String name, UUID branchId) {
        return semesterRepository.findByNameAndBranchId(name, branchId);
    }

    /*
       Get all Semesters by Branch ID
    */
    public List<Semester> findAllSemestersByBranchId(UUID branchId) {
        return semesterRepository.findAllSemestersByBranchId(branchId);
    }

    /*
       Get all Semesters
    */
    public List<Semester> findAll() {
        return semesterRepository.findAll();
    }

    /*
       Delete a Semester
    */
    public void delete(Semester semester) {
        semesterRepository.delete(semester);
    }
}
