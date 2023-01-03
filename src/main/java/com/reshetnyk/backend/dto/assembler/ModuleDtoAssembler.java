package com.reshetnyk.backend.dto.assembler;

import com.reshetnyk.backend.dto.ModuleDto;
import com.reshetnyk.backend.controller.ModuleController;
import com.reshetnyk.backend.domain.Module;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ModuleDtoAssembler implements RepresentationModelAssembler<Module, ModuleDto> {
    @Override
    public ModuleDto toModel(Module entity) {
        ModuleDto moduleDto = ModuleDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .text(entity.getText())
                .video(entity.getVideo())
                .additionalInformation(entity.getAdditionalInformation())
                .orderPosition(entity.getOrderPosition())
                .build();
        Link selfLink = linkTo(methodOn(ModuleController.class).getModule(moduleDto.getId())).withSelfRel();
        moduleDto.add(selfLink);
        return moduleDto;
    }

    @Override
    public CollectionModel<ModuleDto> toCollectionModel(Iterable<? extends Module> entities) {
        CollectionModel<ModuleDto> moduleDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(ModuleController.class).getAllModules()).withSelfRel();
        moduleDtos.add(selfLink);
        return moduleDtos;
    }
}
