package com.microservice.universitycontentservice.Service;

import com.microservice.universitycontentservice.DTO.Mapper.CourseMapper;
import com.microservice.universitycontentservice.DTO.CourseDTO;
import com.microservice.universitycontentservice.Entity.Course;
import com.microservice.universitycontentservice.Entity.Institute;
import com.microservice.universitycontentservice.Exceptions.Course.CourseAlreadyExistsException;
import com.microservice.universitycontentservice.Exceptions.Course.CourseNotFoundException;
import com.microservice.universitycontentservice.Exceptions.Course.CourseServiceException;
import com.microservice.universitycontentservice.Exceptions.Institute.InstituteNotFoundException;
import com.microservice.universitycontentservice.Repository.CourseRepository;
import com.microservice.universitycontentservice.Repository.InstituteRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);


    private final InstituteRepository instituteRepo;
    private final CourseRepository courseRepo;

    @Autowired
    public CourseService(CourseRepository courseRepo, InstituteRepository instituteRepo) {
        this.courseRepo = courseRepo;
        this.instituteRepo = instituteRepo;
    }

    public List<CourseDTO> getAllCoursesService() {
        try {
            List<Course> fetchedCourses = courseRepo.findAll();
            return fetchedCourses.stream()
                    .map(CourseMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in getAllCoursesService: {}", e.getMessage());
            throw new CourseServiceException("An error occurred while fetching all courses. Please try again later.");
        }
    }

    public CourseDTO postCourseService(CourseDTO courseDTO) {
        try {
            Optional<Course> existingCourse = courseRepo.findByName(courseDTO.getName());
            if (existingCourse.isPresent()) {
                throw new CourseAlreadyExistsException("Course already exists");
            }

            UUID instituteId = courseDTO.getInstituteId();

            Institute institute = instituteRepo.findById(courseDTO.getInstituteId())
                    .orElseThrow(() -> new InstituteNotFoundException("Institute not found"));

            Course courseEntity = CourseMapper.toEntity(courseDTO,institute);

            Course savedCourse = courseRepo.save(courseEntity);

            return CourseMapper.toDTO(savedCourse);

        } catch (DataAccessException dae) {
            logger.error("DataAccessException in postCourseService", dae);
            throw new CourseServiceException("An error has occurred while accessing the database.");
        } catch (Exception e) {
            logger.error("Error in postCourseService", e);
            throw new CourseServiceException("An error occurred while creating the course. Please try again later.");
        }
    }

    @Transactional
    public void deleteCourseService(UUID courseId) {
        try {
            Course course = courseRepo.findById(courseId)
                    .orElseThrow(() -> new CourseNotFoundException("Course with Id " + courseId + " not found."));
            courseRepo.deleteById(courseId);
        } catch (DataAccessException dae) {
            logger.error("DataAccessException in deleteCourse: {}", dae.getMessage());
            throw new CourseServiceException("An error has occurred while accessing the database.");
        } catch (CourseNotFoundException e) {
            logger.warn("Course with ID {} not found: {}", courseId, e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error in deleteCourse: {}", e.getMessage());
            throw new CourseServiceException("An error occurred while deleting the course. Please try again later.");
        }
    }

    @Transactional
    public CourseDTO updateCourseService(UUID courseId, Course updatedCourseData) {
        try {
            Course existingCourse = courseRepo.findById(courseId)
                    .orElseThrow(() -> new CourseNotFoundException("Course with Id " + courseId + " not found."));

            existingCourse.setName(updatedCourseData.getName());
            existingCourse.setShortname(updatedCourseData.getShortname());
            existingCourse.setCode(updatedCourseData.getCode());
            existingCourse.setDescription(updatedCourseData.getDescription());

            Course updatedCourse = courseRepo.save(existingCourse);
            return CourseMapper.toDTO(updatedCourse);

        } catch (DataAccessException dae) {
            logger.error("DataAccessException in updateCourse: {}", dae.getMessage());
            throw new CourseServiceException("An error has occurred while accessing the database.");
        } catch (CourseNotFoundException e) {
            logger.warn("Course with ID {} not found for update: {}", courseId, e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error in updateCourse: {}", e.getMessage());
            throw new CourseServiceException("An error occurred while updating the course. Please try again later.");
        }
    }
}
