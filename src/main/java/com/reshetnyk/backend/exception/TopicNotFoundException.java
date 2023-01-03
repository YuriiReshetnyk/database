package com.reshetnyk.backend.exception;

public class TopicNotFoundException extends RuntimeException {
    public TopicNotFoundException(Integer id) {
        super("Could not find 'Topic' with id=" + id);
    }
}
