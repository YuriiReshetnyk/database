package com.reshetnyk.backend.exception;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(Integer id) {
        super("Could not find 'Course' with id=" + id);
    }
}
