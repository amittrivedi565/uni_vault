package com.univault.ims.dao;

import com.univault.ims.entity.Unit;
import com.univault.ims.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UnitDao {

    private final UnitRepository unitRepository;

    @Autowired
    public UnitDao(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    /*
       Save or update a Unit
    */
    public Unit save(Unit unit) {
        return unitRepository.save(unit);
    }

    /*
       Find Unit by ID
    */
    public Optional<Unit> findById(UUID id) {
        return unitRepository.findById(id);
    }

    /*
       Find Unit by Name and Subject ID
    */
    public Optional<Unit> findByNameAndSubjectId(String name, UUID subjectId) {
        return unitRepository.findByNameAndSubjectId(name, subjectId);
    }

    /*
       Get all Units by Subject ID
    */
    public List<Unit> findAllUnitsBySubjectId(UUID subjectId) {
        return unitRepository.findAllUnitsBySubjectId(subjectId);
    }

    /*
       Delete a Unit
    */
    public void delete(Unit unit) {
        unitRepository.delete(unit);
    }
}
