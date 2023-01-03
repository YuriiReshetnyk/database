package com.reshetnyk.backend.dto.assembler;

import com.reshetnyk.backend.dto.ExtraTableDto;
import com.reshetnyk.backend.controller.ExtraTableController;
import com.reshetnyk.backend.domain.ExtraTable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ExtraTableDtoAssembler implements RepresentationModelAssembler<ExtraTable, ExtraTableDto> {
    @Override
    public ExtraTableDto toModel(ExtraTable entity) {
        ExtraTableDto extraTableDto = ExtraTableDto.builder()
                .id(entity.getId())
                .intColum(entity.getIntColum())
                .stringColum(entity.getStringColum())
                .user(entity.getUser().getUserName())
                .build();
        Link selfLink = linkTo(methodOn(ExtraTableController.class).getExtraTable(extraTableDto.getId())).withSelfRel();
        extraTableDto.add(selfLink);
        return extraTableDto;
    }

    @Override
    public CollectionModel<ExtraTableDto> toCollectionModel(Iterable<? extends ExtraTable> entities) {
        CollectionModel<ExtraTableDto> extraTableDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(ExtraTableController.class).getAllExtraTables()).withSelfRel();
        extraTableDtos.add(selfLink);
        return extraTableDtos;
    }
}
