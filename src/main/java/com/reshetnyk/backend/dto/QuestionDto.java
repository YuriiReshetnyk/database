package com.reshetnyk.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.sql.Date;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "question", collectionRelation = "questions")
public class QuestionDto extends RepresentationModel<QuestionDto> {
    private final Integer id;
    private final String question;
    private final String photo;
    private final String test;
}
