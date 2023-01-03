package com.reshetnyk.backend.dto.assembler;

import com.reshetnyk.backend.dto.AnswerOptionDto;
import com.reshetnyk.backend.controller.AnswerOptionController;
import com.reshetnyk.backend.domain.AnswerOption;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class AnswerOptionDtoAssembler implements RepresentationModelAssembler<AnswerOption, AnswerOptionDto> {
    @Override
    public AnswerOptionDto toModel(AnswerOption entity) {
        AnswerOptionDto answerOptionDto = AnswerOptionDto.builder()
                .id(entity.getId())
                .option(entity.getOption())
                .isAnswer(entity.getIsAnswer())
                .question(entity.getQuestion().getQuestion())
                .build();
        Link selfLink = linkTo(methodOn(AnswerOptionController.class).getAnswerOption(answerOptionDto.getId())).withSelfRel();
        answerOptionDto.add(selfLink);
        return answerOptionDto;
    }

    @Override
    public CollectionModel<AnswerOptionDto> toCollectionModel(Iterable<? extends AnswerOption> entities) {
        CollectionModel<AnswerOptionDto> answerOptionDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(AnswerOptionController.class).getAllAnswerOptions()).withSelfRel();
        answerOptionDtos.add(selfLink);
        return answerOptionDtos;
    }
}
