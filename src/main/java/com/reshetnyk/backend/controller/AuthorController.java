package com.reshetnyk.backend.controller;

import com.reshetnyk.backend.domain.Author;
import com.reshetnyk.backend.dto.AuthorDto;
import com.reshetnyk.backend.dto.assembler.AuthorDtoAssembler;
import com.reshetnyk.backend.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorDtoAssembler authorDtoAssembler;

    @GetMapping(value = "/{authorId}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable Integer authorId) {
        Author author = authorService.findById(authorId);
        AuthorDto authorDto = authorDtoAssembler.toModel(author);
        return new ResponseEntity<>(authorDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<AuthorDto>> getAllAuthors() {
        List<Author> authors = authorService.findAll();
        CollectionModel<AuthorDto> authorDtos = authorDtoAssembler.toCollectionModel(authors);
        return new ResponseEntity<>(authorDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<AuthorDto> addAuthor(@RequestBody Author author) {
        Author newAuthor = authorService.create(author);
        AuthorDto authorDto = authorDtoAssembler.toModel(newAuthor);
        return new ResponseEntity<>(authorDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{authorId}")
    public ResponseEntity<?> updateAuthor(@RequestBody Author uAuthor, @PathVariable Integer authorId) {
        authorService.update(authorId, uAuthor);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{authorId}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Integer authorId) {
        authorService.delete(authorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
