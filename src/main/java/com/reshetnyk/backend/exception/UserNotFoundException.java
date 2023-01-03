package com.reshetnyk.backend.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer id) {
        super("Could not find 'User' with id=" + id);
    }
}