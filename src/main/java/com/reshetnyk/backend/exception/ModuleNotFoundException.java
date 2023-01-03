package com.reshetnyk.backend.exception;

public class ModuleNotFoundException extends RuntimeException {
    public ModuleNotFoundException(Integer id) {
        super("Could not find 'Module' with id=" + id);
    }
}