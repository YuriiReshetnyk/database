package com.reshetnyk.backend.exception;

public class UserProgressNotFoundException extends RuntimeException {
    public UserProgressNotFoundException(Integer id) {
        super("Could not find 'UserProgress' with id=" + id);
    }
}
