package com.reshetnyk.backend.service.Impl;

import com.reshetnyk.backend.domain.Author;
import com.reshetnyk.backend.exception.AuthorNotFoundException;
import com.reshetnyk.backend.repository.AuthorRepository;
import com.reshetnyk.backend.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Integer id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }

    @Override
    public Author create(Author author) {
        authorRepository.save(author);
        return author;
    }

    @Override
    public void update(Integer id, Author uAuthor) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
        author.setAuthorInformation(uAuthor.getAuthorInformation());
        author.setFullName(uAuthor.getFullName());
        author.setPhoto(uAuthor.getPhoto());
        authorRepository.save(author);
    }

    @Override
    public void delete(Integer id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
        authorRepository.delete(author);
    }
}
