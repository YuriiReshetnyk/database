package com.reshetnyk.backend.exception;

import com.reshetnyk.backend.domain.Question;

import java.util.List;

public class QuestionExistForTestException extends RuntimeException {
    public QuestionExistForTestException(Integer id, List<Question> question) {
        super("Question " + question + " exist for test with id = " + id);
    }
}