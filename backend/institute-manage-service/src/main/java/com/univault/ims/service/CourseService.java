package com.univault.ims.service;

import com.univault.ims.dto.CourseDTO;
import com.univault.ims.entity.Course;

import java.util.List;
import java.util.UUID;

public interface CourseService {
    CourseDTO getCourseById(UUID id);
    List<CourseDTO> getAllCoursesByInstituteId(UUID id);
    CourseDTO createCourse(CourseDTO courseDTO);
    void deleteCourse(UUID courseId);
    CourseDTO updateCourse(UUID courseId, Course updatedCourseData);
}
