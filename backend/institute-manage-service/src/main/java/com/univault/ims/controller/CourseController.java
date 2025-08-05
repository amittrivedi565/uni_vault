package com.univault.ims.controller;

import com.univault.ims.dto.CourseDTO;
import com.univault.ims.entity.Course;
import com.univault.ims.service.CourseService;
import com.univault.ims.serviceImpl.CourseServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseServiceImpl) {
        this.courseService = courseServiceImpl;
    }

    @GetMapping("/{instituteId}")
    public ResponseEntity<List<CourseDTO>> getAllCoursesByInstituteIdController(@PathVariable UUID instituteId) {
        List<CourseDTO> getAllCoursesByInstituteId = courseService.getAllCoursesByInstituteId(instituteId);
        return new ResponseEntity<>(getAllCoursesByInstituteId, HttpStatus.OK);
    }

    @GetMapping("/fetchbyid/{courseId}")
    public ResponseEntity<CourseDTO> getCourseByIdController(@PathVariable UUID courseId) {
        CourseDTO getCourseById = courseService.getCourseById(courseId);
        return new ResponseEntity<>(getCourseById, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourseController(@RequestBody @Valid CourseDTO dto) {
        CourseDTO createdCourse = courseService.createCourse(dto);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<String> deleteCourseController(@PathVariable UUID courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.ok("Course deleted successfully");
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<CourseDTO> updateCourseController(@PathVariable UUID courseId, @RequestBody Course updatedCourseData) {
        CourseDTO updatedCourse = courseService.updateCourse(courseId, updatedCourseData);
        return ResponseEntity.ok(updatedCourse);
    }
}
