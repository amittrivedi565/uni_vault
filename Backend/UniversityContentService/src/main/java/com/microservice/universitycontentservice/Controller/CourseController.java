package com.microservice.universitycontentservice.Controller;

import com.microservice.universitycontentservice.Dto.Mapper.CourseMapper;
import com.microservice.universitycontentservice.Dto.CourseDTO;
import com.microservice.universitycontentservice.Entity.Course;
import com.microservice.universitycontentservice.Service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCoursesController() {
        List<CourseDTO> fetchAllCourses = service.getAllCoursesService();
        return new ResponseEntity<>(fetchAllCourses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourseController(@RequestBody Course course) {
        Course createdCourse = service.postCourseService(course);
        CourseDTO responseDTO = CourseMapper.toDto(createdCourse);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<String> deleteCourseController(@PathVariable UUID courseId) {
        service.deleteCourseService(courseId);
        return ResponseEntity.status(HttpStatus.OK).body("Course deleted successfully");
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<CourseDTO> updateCourseController(@PathVariable UUID courseId, @RequestBody Course course) {
        CourseDTO updatedCourse = service.updateCourseService(courseId, course);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCourse);
    }
}

