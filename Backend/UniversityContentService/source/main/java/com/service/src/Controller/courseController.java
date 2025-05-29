package com.service.src.Controller;

import com.service.src.Dto.Mapper.courseMapper;
import com.service.src.Dto.Response.courseResponseDto;
import com.service.src.Entity.courseEntity;
import com.service.src.Service.courseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/course")
public class courseController {

    @Autowired
    private courseService service;

    @GetMapping
    public ResponseEntity<List<courseResponseDto>> getAllCoursesController() {
        List<courseResponseDto> fetchAllCourses = service.getAllCoursesService();
        return new ResponseEntity<>(fetchAllCourses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<courseResponseDto> createCourseController(@RequestBody courseEntity course) {
        courseEntity createdCourse = service.postCourseService(course);
        courseResponseDto responseDTO = courseMapper.toDto(createdCourse);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<String> deleteCourseController(@PathVariable UUID courseId) {
        service.deleteCourseService(courseId);
        return ResponseEntity.status(HttpStatus.OK).body("Course deleted successfully");
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<courseResponseDto> updateCourseController(@PathVariable UUID courseId, @RequestBody courseEntity course) {
        courseResponseDto updatedCourse = service.updateCourseService(courseId, course);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCourse);
    }
}

