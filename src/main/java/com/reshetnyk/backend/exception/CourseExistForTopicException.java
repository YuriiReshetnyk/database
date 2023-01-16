package com.reshetnyk.backend.exception;

import com.reshetnyk.backend.domain.Course;

import java.util.List;

public class CourseExistForTopicException extends RuntimeException {
    public CourseExistForTopicException(Integer id, List<Course> courses) {
        super("Course" + courses + " exist for topic with id = " + id);
    }
}