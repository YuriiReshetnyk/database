package com.reshetnyk.backend.exception;

import com.reshetnyk.backend.domain.Module;

import java.util.List;

public class ModuleExistForCourseException extends RuntimeException {
    public ModuleExistForCourseException(Integer id, List<Module> module) {
        super("module " + module + " exist for course with id = " + id);
    }
}

