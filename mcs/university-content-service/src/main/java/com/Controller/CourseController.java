package com.Controller;

import com.DTO.CourseDTO;
import com.Entity.Course;
import com.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCoursesController() {
        List<CourseDTO> allCourses = courseService.getAllCourses();
        return new ResponseEntity<>(allCourses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourseController(@RequestBody CourseDTO dto) {
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
