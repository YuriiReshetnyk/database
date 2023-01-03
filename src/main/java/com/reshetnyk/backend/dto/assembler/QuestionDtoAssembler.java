package com.reshetnyk.backend.dto.assembler;

import com.reshetnyk.backend.dto.QuestionDto;
import com.reshetnyk.backend.controller.QuestionController;
import com.reshetnyk.backend.domain.Question;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class QuestionDtoAssembler implements RepresentationModelAssembler<Question, QuestionDto> {
    @Override
    public QuestionDto toModel(Question entity) {
        QuestionDto questionDto = QuestionDto.builder()
                .id(entity.getId())
                .question(entity.getQuestion())
                .photo(entity.getPhoto())
                .build();
        Link selfLink = linkTo(methodOn(QuestionController.class).getQuestion(questionDto.getId())).withSelfRel();
        questionDto.add(selfLink);
        return questionDto;
    }

    @Override
    public CollectionModel<QuestionDto> toCollectionModel(Iterable<? extends Question> entities) {
        CollectionModel<QuestionDto> questionDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(QuestionController.class).getAllQuestions()).withSelfRel();
        questionDtos.add(selfLink);
        return questionDtos;
    }
}