package com.reshetnyk.backend.dto.assembler;

import com.reshetnyk.backend.dto.UserProgressDto;
import com.reshetnyk.backend.controller.UserProgressController;
import com.reshetnyk.backend.domain.UserProgress;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UserProgressDtoAssembler implements RepresentationModelAssembler<UserProgress, UserProgressDto> {
    @Override
    public UserProgressDto toModel(UserProgress entity) {
        UserProgressDto userProgressDto = UserProgressDto.builder()
                .id(entity.getId())
                .beginTimestamp(entity.getBeginTimestamp())
                .endTimestamp(entity.getEndTimestamp())
                .user(entity.getUser().getUserName())
                .test(entity.getTest().getName())
                .build();
        Link selfLink = linkTo(methodOn(UserProgressController.class).getUserProgress(userProgressDto.getId())).withSelfRel();
        userProgressDto.add(selfLink);
        return userProgressDto;
    }

    @Override
    public CollectionModel<UserProgressDto> toCollectionModel(Iterable<? extends UserProgress> entities) {
        CollectionModel<UserProgressDto> UserProgressDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(UserProgressController.class).getAllUserProgresses()).withSelfRel();
        UserProgressDtos.add(selfLink);
        return UserProgressDtos;
    }
}
