package com.reshetnyk.backend.domain;

import javax.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Test {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "introduction")
    private String introduction;
    @Basic
    @Column(name = "order_position")
    private Integer orderPosition;
    @OneToMany(mappedBy = "test")
    private List<Question> questions;
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
    private Course course;
    @OneToMany(mappedBy = "test")
    private List<UserProgress> userProgresses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getOrderPosition() {
        return orderPosition;
    }

    public void setOrderPosition(Integer orderPosition) {
        this.orderPosition = orderPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test that = (Test) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(introduction, that.introduction) && Objects.equals(orderPosition, that.orderPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, introduction, orderPosition);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<UserProgress> getUserProgresses() {
        return userProgresses;
    }

    public void setUserProgresses(List<UserProgress> userProgresses) {
        this.userProgresses = userProgresses;
    }
}
