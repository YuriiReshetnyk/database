package com.reshetnyk.backend.service;

import com.reshetnyk.backend.domain.Author;
public interface AuthorService extends GeneralService<Author, Integer> {
    void insertIntoAuthor(String fullName, String photo, String authorInformation);
    void insert10IntoAuthor();
}
