package com.univault.ims.serviceImpl;

import com.univault.ims.dto.CourseDTO;
import com.univault.ims.dto.Mapper.CourseMapper;
import com.univault.ims.entity.Course;
import com.univault.ims.entity.Institute;
import com.univault.ims.exception.service.CourseServiceException;
import com.univault.ims.repository.CourseRepository;
import com.univault.ims.repository.InstituteRepository;
import com.univault.ims.service.CourseService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final CourseRepository courseRepo;
    private final InstituteRepository instituteRepo;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepo, InstituteRepository instituteRepo) {
        this.courseRepo = courseRepo;
        this.instituteRepo = instituteRepo;
    }

    @Override
    public CourseDTO getCourseById(UUID id) {
        return courseRepo.findById(id)
                .map(CourseMapper::toDTO)
                .orElseThrow(() -> {
                    String message = "Course not found with ID: " + id;
                    logger.warn(message);
                    return new CourseServiceException(message);
                });
    }

    @Override
    public List<CourseDTO> getAllCoursesByInstituteId(UUID instituteId) {
        try {
            List<Course> courses = courseRepo.findAllCoursesByInstituteId(instituteId);
            if (courses.isEmpty()) {
                String message = "No Courses found for Institute ID: " + instituteId;
                logger.warn(message);
                throw new CourseServiceException(message);
            }
            return courses.stream()
                    .map(course -> CourseMapper.toDTO(course, true))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in getAllCoursesByInstituteId", e);
            throw new CourseServiceException("An error occurred while fetching courses by institute id.", e);
        }
    }

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        courseRepo.findByNameAndInstituteId(courseDTO.getName(), courseDTO.getInstituteId())
                .ifPresent(existing -> {
                    String message = "Course already exists with name: " + courseDTO.getName();
                    logger.warn(message);
                    throw new CourseServiceException(message);
                });

        Institute institute = instituteRepo.findById(courseDTO.getInstituteId())
                .orElseThrow(() -> {
                    String message = "Institute not found with ID: " + courseDTO.getInstituteId();
                    logger.warn(message);
                    return new CourseServiceException(message);
                });

        try {
            Course courseEntity = CourseMapper.toEntity(courseDTO);
            courseEntity.setInstitute(institute);

            Course savedCourse = courseRepo.save(courseEntity);
            logger.info("Course created successfully with ID: {}", savedCourse.getId());
            return CourseMapper.toDTO(savedCourse);
        } catch (Exception e) {
            logger.error("Error in createCourse", e);
            throw new CourseServiceException("An error occurred while creating the course.", e);
        }
    }

    @Override
    @Transactional
    public void deleteCourse(UUID courseId) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> {
                    String message = "Course not found with ID: " + courseId;
                    logger.warn(message);
                    return new CourseServiceException(message);
                });

        try {
            courseRepo.delete(course);
            logger.info("Course deleted successfully with ID: {}", courseId);
        } catch (Exception e) {
            logger.error("Error in deleteCourse", e);
            throw new CourseServiceException("An error occurred while deleting the course.", e);
        }
    }

    @Override
    @Transactional
    public CourseDTO updateCourse(UUID courseId, Course updatedCourseData) {
        Course existingCourse = courseRepo.findById(courseId)
                .orElseThrow(() -> {
                    String message = "Course not found with ID: " + courseId;
                    logger.warn(message);
                    return new CourseServiceException(message);
                });

        existingCourse.setName(updatedCourseData.getName());
        existingCourse.setShortname(updatedCourseData.getShortname());
        existingCourse.setCode(updatedCourseData.getCode());
        existingCourse.setDescription(updatedCourseData.getDescription());

        try {
            Course updatedCourse = courseRepo.save(existingCourse);
            logger.info("Course updated successfully with ID: {}", courseId);
            return CourseMapper.toDTO(updatedCourse);
        } catch (Exception e) {
            logger.error("Error in updateCourse", e);
            throw new CourseServiceException("An error occurred while updating the course.", e);
        }
    }
}
