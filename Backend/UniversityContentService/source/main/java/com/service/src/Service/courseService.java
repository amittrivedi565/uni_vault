package com.service.src.Service;

import com.service.src.Dto.Mapper.courseMapper;
import com.service.src.Dto.Response.courseResponseDto;
import com.service.src.Entity.courseEntity;
import com.service.src.Exceptions.Course.courseAlreadyExistsException;
import com.service.src.Exceptions.Course.courseNotFoundException;
import com.service.src.Exceptions.Course.courseServiceException;
import com.service.src.Repository.courseRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class courseService {

    // Initialize logger
    private static final Logger logger = LoggerFactory.getLogger(courseService.class);

    private final courseRepository courseRepo;

    public courseService(courseRepository courseRepo) {
        this.courseRepo = courseRepo;
    }

    // Get all courses
    public List<courseResponseDto> getAllCoursesService() {
        try {
            List<courseEntity> fetchedCourses = courseRepo.findAll();
            return fetchedCourses.stream()
                    .map(courseMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in getAllCoursesService: {}", e.getMessage());
            throw new courseServiceException("An error occurred while fetching all courses. Please try again later.");
        }
    }

    // Create a new course
    public courseEntity postCourseService(courseEntity course) {
        try {
            Optional<courseEntity> existingCourse = courseRepo.findByName(course.getName());
            if (existingCourse.isPresent()) {
                throw new courseAlreadyExistsException("Course " + course.getName() + " already exists.");
            }
            return courseRepo.save(course);
        } catch (DataAccessException dae) {
            logger.error("DataAccessException in postCourseService: {}", dae.getMessage());
            throw new courseServiceException("An error has occurred while accessing the database.");
        } catch (Exception e) {
            logger.error("Error in postCourseService: {}", e.getMessage());
            throw new courseServiceException("An error occurred while creating the course. Please try again later.");
        }
    }

    // Delete a course by ID
    @Transactional
    public void deleteCourseService(UUID courseId) {
        try {
            courseEntity course = courseRepo.findById(courseId)
                    .orElseThrow(() -> new courseNotFoundException("Course with Id " + courseId + " not found."));
            courseRepo.deleteById(courseId);
        } catch (DataAccessException dae) {
            logger.error("DataAccessException in deleteCourse: {}", dae.getMessage());
            throw new courseServiceException("An error has occurred while accessing the database.");
        } catch (courseNotFoundException e) {
            logger.warn("Course with ID {} not found: {}", courseId, e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error in deleteCourse: {}", e.getMessage());
            throw new courseServiceException("An error occurred while deleting the course. Please try again later.");
        }
    }

    // Update an existing course
    @Transactional
    public courseResponseDto updateCourseService(UUID courseId, courseEntity updatedCourseData) {
        try {
            courseEntity existingCourse = courseRepo.findById(courseId)
                    .orElseThrow(() -> new courseNotFoundException("Course with Id " + courseId + " not found."));

            existingCourse.setName(updatedCourseData.getName());
            existingCourse.setShortname(updatedCourseData.getShortname());
            existingCourse.setCode(updatedCourseData.getCode());
            existingCourse.setDescription(updatedCourseData.getDescription());

            courseEntity updatedCourse = courseRepo.save(existingCourse);
            return courseMapper.toDto(updatedCourse);
        } catch (DataAccessException dae) {
            logger.error("DataAccessException in updateCourse: {}", dae.getMessage());
            throw new courseServiceException("An error has occurred while accessing the database.");
        } catch (courseNotFoundException e) {
            logger.warn("Course with ID {} not found for update: {}", courseId, e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error in updateCourse: {}", e.getMessage());
            throw new courseServiceException("An error occurred while updating the course. Please try again later.");
        }
    }
}
