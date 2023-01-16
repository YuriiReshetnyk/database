package com.reshetnyk.backend.service.Impl;

import com.reshetnyk.backend.domain.Course;
import com.reshetnyk.backend.exception.CourseNotFoundException;
import com.reshetnyk.backend.exception.ModuleExistForCourseException;
import com.reshetnyk.backend.exception.TestExistForCourseException;
import com.reshetnyk.backend.repository.CourseRepository;
import com.reshetnyk.backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Integer id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
    }

    @Override
    public Course create(Course course) {
        courseRepository.save(course);
        return course;
    }

    @Override
    public void update(Integer id, Course uCourse) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
        course.setName(uCourse.getName());
        course.setDuration(uCourse.getDuration());
        course.setPrice(uCourse.getPrice());
        course.setIntroduction(uCourse.getIntroduction());
        course.setStartTime(uCourse.getStartTime());
        courseRepository.save(course);
    }

    @Override
    public void delete(Integer id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
        if (!course.getModules().isEmpty()) throw new ModuleExistForCourseException(id, course.getModules());
        if (!course.getTests().isEmpty()) throw new TestExistForCourseException(id, course.getTests());
        courseRepository.delete(course);
    }
}
