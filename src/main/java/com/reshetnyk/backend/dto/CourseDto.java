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
@Relation(itemRelation = "course", collectionRelation = "courses")
public class CourseDto extends RepresentationModel<CourseDto> {
    private final Integer id;
    private final String name;
    private final Integer duration;
    private final int price;
    private final String introduction;
    private final Date startTime;
    private final String topic;
}
