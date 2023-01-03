package com.reshetnyk.backend.exception;

import com.reshetnyk.backend.domain.UserProgress;

import java.util.List;

public class UserProgressExistForUserException extends RuntimeException {
    public UserProgressExistForUserException(Integer id, List<UserProgress> userProgress) {
        super("UserProgress" + userProgress + " exist for user with id = " + id);
    }
}