package com.reshetnyk.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import com.reshetnyk.backend.domain.AnswerOption;
import com.reshetnyk.backend.dto.AnswerOptionDto;
import com.reshetnyk.backend.dto.assembler.AnswerOptionDtoAssembler;
import com.reshetnyk.backend.service.AnswerOptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/answerOption")
public class AnswerOptionController {
    @Autowired
    private AnswerOptionService answerOptionService;
    @Autowired
    private AnswerOptionDtoAssembler answerOptionDtoAssembler;

    @GetMapping(value = "/{answerOptionId}")
    public ResponseEntity<AnswerOptionDto> getAnswerOption(@PathVariable Integer answerOptionId) {
        AnswerOption answerOption = answerOptionService.findById(answerOptionId);
        AnswerOptionDto answerOptionDto = answerOptionDtoAssembler.toModel(answerOption);
        return new ResponseEntity<>(answerOptionDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<AnswerOptionDto>> getAllAnswerOptions() {
        List<AnswerOption> answerOptions = answerOptionService.findAll();
        CollectionModel<AnswerOptionDto> answerOptionDtos = answerOptionDtoAssembler.toCollectionModel(answerOptions);
        return new ResponseEntity<>(answerOptionDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<AnswerOptionDto> addAnswerOption(@RequestBody AnswerOption answerOption) {
        AnswerOption newAnswerOption = answerOptionService.create(answerOption);
        AnswerOptionDto answerOptionDto = answerOptionDtoAssembler.toModel(newAnswerOption);
        return new ResponseEntity<>(answerOptionDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{answerOptionId}")
    public ResponseEntity<?> updateAnswerOption(@RequestBody AnswerOption uAnswerOption, @PathVariable Integer answerOptionId) {
        answerOptionService.update(answerOptionId, uAnswerOption);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{answerOptionId}")
    public ResponseEntity<?> deleteAnswerOption(@PathVariable Integer answerOptionId) {
        answerOptionService.delete(answerOptionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
