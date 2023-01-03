package com.reshetnyk.backend.controller;

import com.reshetnyk.backend.domain.Test;
import com.reshetnyk.backend.dto.TestDto;
import com.reshetnyk.backend.dto.assembler.TestDtoAssembler;
import com.reshetnyk.backend.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/test")
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private TestDtoAssembler testDtoAssembler;

    @GetMapping(value = "/{testId}")
    public ResponseEntity<TestDto> getTest(@PathVariable Integer testId) {
        Test test = testService.findById(testId);
        TestDto testDto = testDtoAssembler.toModel(test);
        return new ResponseEntity<>(testDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<TestDto>> getAllTests() {
        List<Test> tests = testService.findAll();
        CollectionModel<TestDto> testDtos = testDtoAssembler.toCollectionModel(tests);
        return new ResponseEntity<>(testDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<TestDto> addTest(@RequestBody Test test) {
        Test tests = testService.create(test);
        TestDto questionDto = testDtoAssembler.toModel(tests);
        return new ResponseEntity<>(questionDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{testId}")
    public ResponseEntity<?> updateTest(@RequestBody Test uTest, @PathVariable Integer testId) {
        testService.update(testId, uTest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{testId}")
    public ResponseEntity<?> deleteTest(@PathVariable Integer testId) {
        testService.delete(testId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}