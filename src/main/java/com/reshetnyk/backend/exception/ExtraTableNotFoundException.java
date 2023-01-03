package com.reshetnyk.backend.exception;

public class ExtraTableNotFoundException extends RuntimeException {
    public ExtraTableNotFoundException(Integer id) {
        super("Could not find 'ExtraTable' with id=" + id);
    }
}