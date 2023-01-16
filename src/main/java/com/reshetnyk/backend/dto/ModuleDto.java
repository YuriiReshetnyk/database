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
@Relation(itemRelation = "module", collectionRelation = "modules")
public class ModuleDto extends RepresentationModel<ModuleDto> {
    private final Integer id;
    private final String name;
    private final String text;
    private final String video;
    private final String additionalInformation;
    private final int orderPosition;
    private final String course;
}
