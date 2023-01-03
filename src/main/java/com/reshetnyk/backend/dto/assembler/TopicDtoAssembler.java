package com.reshetnyk.backend.dto.assembler;

import com.reshetnyk.backend.dto.TopicDto;
import com.reshetnyk.backend.controller.TopicController;
import com.reshetnyk.backend.domain.Topic;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class TopicDtoAssembler implements RepresentationModelAssembler<Topic, TopicDto> {
    @Override
    public TopicDto toModel(Topic entity) {
        TopicDto topicDto = TopicDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
        Link selfLink = linkTo(methodOn(TopicController.class).getTopic(topicDto.getId())).withSelfRel();
        topicDto.add(selfLink);
        return topicDto;
    }

    @Override
    public CollectionModel<TopicDto> toCollectionModel(Iterable<? extends Topic> entities) {
        CollectionModel<TopicDto> topicDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(TopicController.class).getAllTopics()).withSelfRel();
        topicDtos.add(selfLink);
        return topicDtos;
    }
}
