package com.reshetnyk.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "answerOption", collectionRelation = "answerOptions")
public class AnswerOptionDto extends RepresentationModel<AnswerOptionDto> {
    private final Integer id;
    private final String option;
    private final byte isAnswer;
    private final String question;
}
