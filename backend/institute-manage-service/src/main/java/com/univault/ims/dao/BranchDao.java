package com.univault.ims.dao;

import com.univault.ims.entity.Branch;
import com.univault.ims.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class BranchDao {

    private final BranchRepository branchRepository;

    @Autowired
    public BranchDao(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    /*
       Save or update a Branch
    */
    public Branch save(Branch branch) {
        return branchRepository.save(branch);
    }

    /*
       Find a Branch by UUID
    */
    public Optional<Branch> findById(UUID id) {
        return branchRepository.findById(id);
    }

    /*
       Find a Branch by name and course ID
    */
    public Optional<Branch> findByNameAndCourseId(String name, UUID courseId) {
        return branchRepository.findByNameAndCourseId(name, courseId);
    }

    /*
       Get all branches for a specific course
    */
    public List<Branch> findAllBranchesByCourseId(UUID courseId) {
        return branchRepository.findAllBranchesByCourseId(courseId);
    }

    /*
       Delete a Branch
    */
    public void delete(Branch branch) {
        branchRepository.delete(branch);
    }

    /*
       Get all branches
    */
    public List<Branch> findAll() {
        return branchRepository.findAll();
    }
}
