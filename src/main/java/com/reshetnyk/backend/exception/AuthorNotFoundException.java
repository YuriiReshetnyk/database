package com.reshetnyk.backend.exception;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(Integer id) {
        super("Could not find 'Author' with id=" + id);
    }
}