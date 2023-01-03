package com.reshetnyk.backend.dto.assembler;

import com.reshetnyk.backend.dto.CourseDto;
import com.reshetnyk.backend.controller.CourseController;
import com.reshetnyk.backend.domain.Course;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CourseDtoAssembler implements RepresentationModelAssembler<Course, CourseDto> {
    @Override
    public CourseDto toModel(Course entity) {
        CourseDto courseDto = CourseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .duration(entity.getDuration())
                .price(entity.getPrice())
                .introduction(entity.getIntroduction())
                .startTime(entity.getStartTime())
                .build();
        Link selfLink = linkTo(methodOn(CourseController.class).getCourse(courseDto.getId())).withSelfRel();
        courseDto.add(selfLink);
        return courseDto;
    }

    @Override
    public CollectionModel<CourseDto> toCollectionModel(Iterable<? extends Course> entities) {
        CollectionModel<CourseDto> answerOptionsDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CourseController.class).getAllCourses()).withSelfRel();
        answerOptionsDtos.add(selfLink);
        return answerOptionsDtos;
    }
}
