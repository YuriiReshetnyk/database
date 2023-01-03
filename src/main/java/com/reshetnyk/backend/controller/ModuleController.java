package com.reshetnyk.backend.controller;

import com.reshetnyk.backend.domain.Module;
import com.reshetnyk.backend.dto.ModuleDto;
import com.reshetnyk.backend.dto.assembler.ModuleDtoAssembler;
import com.reshetnyk.backend.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/module")
public class ModuleController {
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ModuleDtoAssembler moduleDtoAssembler;

    @GetMapping(value = "/{moduleId}")
    public ResponseEntity<ModuleDto> getModule(@PathVariable Integer moduleId) {
        Module module = moduleService.findById(moduleId);
        ModuleDto moduleDto = moduleDtoAssembler.toModel(module);
        return new ResponseEntity<>(moduleDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<ModuleDto>> getAllModules() {
        List<Module> modules = moduleService.findAll();
        CollectionModel<ModuleDto> moduleDtos = moduleDtoAssembler.toCollectionModel(modules);
        return new ResponseEntity<>(moduleDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<ModuleDto> addModule(@RequestBody Module module) {
        Module modules = moduleService.create(module);
        ModuleDto moduleDto = moduleDtoAssembler.toModel(modules);
        return new ResponseEntity<>(moduleDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{moduleId}")
    public ResponseEntity<?> updateModule(@RequestBody Module uModule, @PathVariable Integer moduleId) {
        moduleService.update(moduleId, uModule);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{moduleId}")
    public ResponseEntity<?> deleteModule(@PathVariable Integer moduleId) {
        moduleService.delete(moduleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}