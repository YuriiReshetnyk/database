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
@Relation(itemRelation = "extraTable", collectionRelation = "extraTables")
public class ExtraTableDto extends RepresentationModel<ExtraTableDto> {
    private final Integer id;
    private final Integer intColum;
    private final String stringColum;
    private final String user;
}
