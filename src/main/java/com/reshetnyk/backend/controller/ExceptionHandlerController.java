package com.reshetnyk.backend.controller;

import com.reshetnyk.backend.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ResponseBody
    @ExceptionHandler(AnswerOptionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String answerOptionsNotFoundHandler(AnswerOptionNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(AuthorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String authorNotFoundHandler(AuthorNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CourseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String courseNotFoundHandler(CourseNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ModuleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String moduleNotFoundHandler(ModuleNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(QuestionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String questionNotFoundHandler(QuestionNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TestNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String testNotFoundHandler(TestNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TopicNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String topicNotFoundHandler(TopicNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userNotFoundHandler(UserNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserProgressNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userProgressNotFoundHandler(UserProgressNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ExtraTableNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String extraTableNotFoundHandler(ExtraTableNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserProgressExistForUserException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String userProgressExistForUserHandler(UserProgressExistForUserException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserProgressExistForTestException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String UserProgressExistForTestException(UserProgressExistForTestException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(AnswerOptionsExistForTestException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String AnswerOptionsExistForTestException(AnswerOptionsExistForTestException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CourseExistForTopicException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String courseExistForTopicHandler(CourseExistForTopicException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ModuleExistForCourseException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String moduleExistForCourseHandler(ModuleExistForCourseException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(QuestionExistForTestException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String questionExistForTestHandler(QuestionExistForTestException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TestExistForCourseException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String testExistForCourseHandler(TestExistForCourseException ex) {
        return ex.getMessage();
    }
}