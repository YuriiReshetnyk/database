package com.reshetnyk.backend.repository;

import com.reshetnyk.backend.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Procedure("insert_into_author")
    void insertIntoAuthor(String fullName, String photo, String authorInformation);
    @Procedure("insert_10_into_author")
    void insert10IntoAuthor();
}