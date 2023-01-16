package com.reshetnyk.backend.controller;

import com.reshetnyk.backend.domain.UserProgress;
import com.reshetnyk.backend.dto.UserProgressDto;
import com.reshetnyk.backend.dto.assembler.UserProgressDtoAssembler;
import com.reshetnyk.backend.service.UserProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/userProgress")
public class UserProgressController {
    @Autowired
    private UserProgressService userProgressService;
    @Autowired
    private UserProgressDtoAssembler userProgressDtoAssembler;

    @GetMapping(value = "/{userProgressId}")
    public ResponseEntity<UserProgressDto> getUserProgress(@PathVariable Integer userProgressId) {
        UserProgress userProgress = userProgressService.findById(userProgressId);
        UserProgressDto userProgressDto = userProgressDtoAssembler.toModel(userProgress);
        return new ResponseEntity<>(userProgressDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<UserProgressDto>> getAllUserProgresses() {
        List<UserProgress> userProgresses = userProgressService.findAll();
        CollectionModel<UserProgressDto> userProgressDtos = userProgressDtoAssembler.toCollectionModel(userProgresses);
        return new ResponseEntity<>(userProgressDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<UserProgressDto> addUserProgress(@RequestBody UserProgress userProgress) {
        UserProgress newUserProgress = userProgressService.create(userProgress);
        UserProgressDto newUserProgressDto = userProgressDtoAssembler.toModel(newUserProgress);
        return new ResponseEntity<>(newUserProgressDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{userProgressId}")
    public ResponseEntity<?> updateUserProgress(@RequestBody UserProgress uUserProgress, @PathVariable Integer userProgressId) {
        userProgressService.update(userProgressId, uUserProgress);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userProgressId}")
    public ResponseEntity<?> deleteUserProgress(@PathVariable Integer userProgressId) {
        userProgressService.delete(userProgressId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
