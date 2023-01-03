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
@Relation(itemRelation = "test", collectionRelation = "tests")
public class TestDto extends RepresentationModel<TestDto> {
    private final Integer id;
    private final String name;
    private final String introduction;
    private final int orderPosition;
    private final String course;
}
