package com.reshetnyk.backend.controller;

import com.reshetnyk.backend.domain.Question;
import com.reshetnyk.backend.dto.QuestionDto;
import com.reshetnyk.backend.dto.assembler.QuestionDtoAssembler;
import com.reshetnyk.backend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionDtoAssembler questionDtoAssembler;

    @GetMapping(value = "/{questionId}")
    public ResponseEntity<QuestionDto> getQuestion(@PathVariable Integer questionId) {
        Question question = questionService.findById(questionId);
        QuestionDto questionDto = questionDtoAssembler.toModel(question);
        return new ResponseEntity<>(questionDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<QuestionDto>> getAllQuestions() {
        List<Question> questions = questionService.findAll();
        CollectionModel<QuestionDto> questionDtos = questionDtoAssembler.toCollectionModel(questions);
        return new ResponseEntity<>(questionDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<QuestionDto> addQuestion(@RequestBody Question question) {
        Question questions = questionService.create(question);
        QuestionDto questionDto = questionDtoAssembler.toModel(questions);
        return new ResponseEntity<>(questionDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{questionId}")
    public ResponseEntity<?> updateQuestion(@RequestBody Question uQuestion, @PathVariable Integer questionId) {
        questionService.update(questionId, uQuestion);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{questionId}")
    public ResponseEntity<?> deleteModule(@PathVariable Integer questionId) {
        questionService.delete(questionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
