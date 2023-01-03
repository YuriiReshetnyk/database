package com.reshetnyk.backend.dto.assembler;

import com.reshetnyk.backend.dto.TestDto;
import com.reshetnyk.backend.controller.TestController;
import com.reshetnyk.backend.domain.Test;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class TestDtoAssembler implements RepresentationModelAssembler<Test, TestDto> {
    @Override
    public TestDto toModel(Test entity) {
        TestDto testDto = TestDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .introduction(entity.getIntroduction())
                .orderPosition(entity.getOrderPosition())
                .build();
        Link selfLink = linkTo(methodOn(TestController.class).getTest(testDto.getId())).withSelfRel();
        testDto.add(selfLink);
        return testDto;
    }

    @Override
    public CollectionModel<TestDto> toCollectionModel(Iterable<? extends Test> entities) {
        CollectionModel<TestDto> testDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(TestController.class).getAllTests()).withSelfRel();
        testDtos.add(selfLink);
        return testDtos;
    }
}
