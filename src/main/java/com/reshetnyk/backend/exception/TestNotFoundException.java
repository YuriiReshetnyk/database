package com.reshetnyk.backend.exception;

public class TestNotFoundException extends RuntimeException {
    public TestNotFoundException(Integer id) {
        super("Could not find 'Test' with id=" + id);
    }
}