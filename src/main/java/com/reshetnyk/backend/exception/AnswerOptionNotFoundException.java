package com.reshetnyk.backend.exception;

public class AnswerOptionNotFoundException extends RuntimeException {
    public AnswerOptionNotFoundException(Integer id) {
        super("Could not find 'AnswerOptions' with id=" + id);
    }
}
