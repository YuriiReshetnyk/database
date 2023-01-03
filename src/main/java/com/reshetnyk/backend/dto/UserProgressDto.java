package com.reshetnyk.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.sql.Timestamp;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "userProgress", collectionRelation = "userProgresses")
public class UserProgressDto extends RepresentationModel<UserProgressDto> {
    private final Integer id;
    private final Timestamp beginTimestamp;
    private final Timestamp endTimestamp;
    private final String user;
    private final String test;
}
