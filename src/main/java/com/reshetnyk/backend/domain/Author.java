package com.reshetnyk.backend.domain;

import javax.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
public class Author {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "full_name")
    private String fullName;
    @Basic
    @Column(name = "photo")
    private String photo;
    @Basic
    @Column(name = "author_information")
    private String authorInformation;
    @ManyToMany
    @JoinTable(name = "course_author", catalog = "", schema = "reshetnyk", joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false))
    private Set<Course> courses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAuthorInformation() {
        return authorInformation;
    }

    public void setAuthorInformation(String authorInformation) {
        this.authorInformation = authorInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author that = (Author) o;
        return Objects.equals(id, that.id) && Objects.equals(fullName, that.fullName) && Objects.equals(photo, that.photo) && Objects.equals(authorInformation, that.authorInformation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, photo, authorInformation);
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
