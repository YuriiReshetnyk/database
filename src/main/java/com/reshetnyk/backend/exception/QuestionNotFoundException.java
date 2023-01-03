package com.reshetnyk.backend.exception;

public class QuestionNotFoundException extends RuntimeException {
    public QuestionNotFoundException(Integer id) {
        super("Could not find 'Question' with id=" + id);
    }
}
