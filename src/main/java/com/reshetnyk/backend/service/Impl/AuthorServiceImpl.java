package com.reshetnyk.backend.service.Impl;

import com.reshetnyk.backend.domain.Author;
import com.reshetnyk.backend.exception.AuthorNotFoundException;
import com.reshetnyk.backend.repository.AuthorRepository;
import com.reshetnyk.backend.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author findById(Integer id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }

    @Transactional
    public Author create(Author author) {
        authorRepository.save(author);
        return author;
    }

    @Transactional
    public void update(Integer id, Author uAuthor) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
        author.setAuthorInformation(uAuthor.getAuthorInformation());
        author.setFullName(uAuthor.getFullName());
        author.setPhoto(uAuthor.getPhoto());
        authorRepository.save(author);
    }

    @Transactional
    public void delete(Integer id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
        authorRepository.delete(author);
    }

    @Override
    public void insertIntoAuthor(String fullName, String photo, String authorInformation){
        authorRepository.insertIntoAuthor(fullName, photo, authorInformation);
    }

    @Override
    public void insert10IntoAuthor(){
        authorRepository.insert10IntoAuthor();
    }
}
