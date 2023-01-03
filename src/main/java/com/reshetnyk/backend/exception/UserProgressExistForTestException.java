package com.reshetnyk.backend.exception;

import com.reshetnyk.backend.domain.UserProgress;

import java.util.List;

public class UserProgressExistForTestException extends RuntimeException {
    public UserProgressExistForTestException(Integer id, List<UserProgress> userProgresses) {
        super("UserProgress" + userProgresses + " exist for topic with id = " + id);
    }
}