package com.reshetnyk.backend.dto.assembler;

import com.reshetnyk.backend.dto.AuthorDto;
import com.reshetnyk.backend.controller.AuthorController;
import com.reshetnyk.backend.domain.Author;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class AuthorDtoAssembler implements RepresentationModelAssembler<Author, AuthorDto> {
    @Override
    public AuthorDto toModel(Author entity) {
        AuthorDto authorDto = AuthorDto.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .photo(entity.getPhoto())
                .authorInformation(entity.getAuthorInformation())
                .build();
        Link selfLink = linkTo(methodOn(AuthorController.class).getAuthor(authorDto.getId())).withSelfRel();
        authorDto.add(selfLink);
        return authorDto;
    }

    @Override
    public CollectionModel<AuthorDto> toCollectionModel(Iterable<? extends Author> entities) {
        CollectionModel<AuthorDto> authorDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(AuthorController.class).getAllAuthors()).withSelfRel();
        authorDtos.add(selfLink);
        return authorDtos;
    }
}
