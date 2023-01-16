package com.reshetnyk.backend.controller;

import com.reshetnyk.backend.domain.Course;
import com.reshetnyk.backend.dto.CourseDto;
import com.reshetnyk.backend.dto.assembler.CourseDtoAssembler;
import com.reshetnyk.backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseDtoAssembler courseDtoAssembler;

    @GetMapping(value = "/{courseId}")
    public ResponseEntity<CourseDto> getCourse(@PathVariable Integer courseId) {
        Course course = courseService.findById(courseId);
        CourseDto courseDto = courseDtoAssembler.toModel(course);
        return new ResponseEntity<>(courseDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CourseDto>> getAllCourses() {
        List<Course> course = courseService.findAll();
        CollectionModel<CourseDto> courseDtos = courseDtoAssembler.toCollectionModel(course);
        return new ResponseEntity<>(courseDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<CourseDto> addCourse(@RequestBody Course answerOptions) {
        Course course = courseService.create(answerOptions);
        CourseDto courseDto = courseDtoAssembler.toModel(course);
        return new ResponseEntity<>(courseDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{courseId}")
    public ResponseEntity<?> updateCourse(@RequestBody Course uCourse, @PathVariable Integer courseId) {
        courseService.update(courseId, uCourse);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Integer courseId) {
        courseService.delete(courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
