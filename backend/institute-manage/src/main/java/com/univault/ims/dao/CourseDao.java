package com.univault.ims.dao;

import com.univault.ims.entity.Course;
import com.univault.ims.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CourseDao {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseDao(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /*
       Save or update a Course
    */
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    /*
       Find a Course by UUID
    */
    public Optional<Course> findById(UUID id) {
        return courseRepository.findById(id);
    }

    /*
       Find a Course by name and institute ID
    */
    public Optional<Course> findByNameAndInstituteId(String name, UUID instituteId) {
        return courseRepository.findByNameAndInstituteId(name, instituteId);
    }

    /*
       Get all Courses for a specific Institute
    */
    public List<Course> findAllByInstituteId(UUID instituteId) {
        return courseRepository.findAllCoursesByInstituteId(instituteId);
    }

    /*
       Get all Courses
    */
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    /*
       Delete a Course
    */
    public void delete(Course course) {
        courseRepository.delete(course);
    }
}
