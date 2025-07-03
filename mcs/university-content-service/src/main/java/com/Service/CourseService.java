package com.Service;

import com.DTO.CourseDTO;
import com.DTO.Mapper.CourseMapper;
import com.Entity.Course;
import com.Entity.Institute;
import com.Exceptions.Course.CourseServiceException;
import com.Repository.CourseRepository;
import com.Repository.InstituteRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

    private final CourseRepository courseRepo;
    private final InstituteRepository instituteRepo;

    @Autowired
    public CourseService(CourseRepository courseRepo, InstituteRepository instituteRepo) {
        this.courseRepo = courseRepo;
        this.instituteRepo = instituteRepo;
    }

    public List<CourseDTO> getAllCourses() {
        try {
            List<Course> courses = courseRepo.findAll();
            return courses.stream()
                    .map(CourseMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (CourseServiceException ex) {
            throw ex;
        } catch (Exception e) {
            logger.error("Error in getAllCourses: {}", e.getMessage());
            throw new CourseServiceException("An error occurred while fetching all courses. Please try again later.");
        }
    }

    public CourseDTO createCourse(CourseDTO courseDTO) {
        try {
            Optional<Course> existingCourse = courseRepo.findByNameAndInstituteId(courseDTO.getName(), courseDTO.getInstituteId());
            if (existingCourse.isPresent()) {
                throw new CourseServiceException("Course already exists with this name.");
            }

            Institute institute = instituteRepo.findById(courseDTO.getInstituteId())
                    .orElseThrow(() -> new CourseServiceException("Institute not found with this ID."));

            Course courseEntity = CourseMapper.toEntity(courseDTO);

            courseEntity.setInstitute(institute);

            Course savedCourse = courseRepo.save(courseEntity);

            return CourseMapper.toDTO(savedCourse);

        } catch (CourseServiceException ex) {
            throw ex;
        } catch (Exception e) {
            logger.error("Error in createCourse", e);
            throw new CourseServiceException("An error occurred while creating the course. Please try again later.");
        }
    }

    @Transactional
    public void deleteCourse(UUID courseId) {
        try {
            courseRepo.findById(courseId)
                    .orElseThrow(() -> new CourseServiceException("Course with ID " + courseId + " not found."));

            courseRepo.deleteById(courseId);

        } catch (CourseServiceException ex) {
            throw ex;
        } catch (Exception e) {
            logger.error("Error in deleteCourse: {}", e.getMessage());
            throw new CourseServiceException("An error occurred while deleting the course. Please try again later.");
        }
    }

    @Transactional
    public CourseDTO updateCourse(UUID courseId, Course updatedCourseData) {
        try {
            Course existingCourse = courseRepo.findById(courseId)
                    .orElseThrow(() -> new CourseServiceException("Course with ID " + courseId + " not found."));

            existingCourse.setName(updatedCourseData.getName());
            existingCourse.setShortname(updatedCourseData.getShortname());
            existingCourse.setCode(updatedCourseData.getCode());
            existingCourse.setDescription(updatedCourseData.getDescription());

            Course updatedCourse = courseRepo.save(existingCourse);
            return CourseMapper.toDTO(updatedCourse);

        } catch (CourseServiceException ex) {
            throw ex;
        } catch (Exception e) {
            logger.error("Error in updateCourse: {}", e.getMessage());
            throw new CourseServiceException("An error occurred while updating the course. Please try again later.");
        }
    }
}
