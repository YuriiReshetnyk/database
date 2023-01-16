package com.reshetnyk.backend.exception;

import com.reshetnyk.backend.domain.Test;

import java.util.List;

public class TestExistForCourseException extends RuntimeException {
    public TestExistForCourseException(Integer id, List<Test> test ) {
        super("test " + test + " exist for course with id = " + id);
    }
}

