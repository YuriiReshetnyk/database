package com.reshetnyk.backend.exception;

import com.reshetnyk.backend.domain.AnswerOption;

import java.util.List;

public class AnswerOptionsExistForTestException extends RuntimeException {
    public AnswerOptionsExistForTestException(Integer id, List<AnswerOption> answerOptions) {
        super("AnswerOptions" + answerOptions + " exist for topic with id = " + id);
    }
}
