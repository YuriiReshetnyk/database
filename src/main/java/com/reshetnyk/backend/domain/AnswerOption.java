package com.reshetnyk.backend.domain;

import javax.persistence.*;

import java.util.Objects;

@Entity
public class AnswerOption {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "option")
    private String option;
    @Basic
    @Column(name = "is_answer")
    private Byte isAnswer;
    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id", nullable = false)
    private Question question;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Byte getIsAnswer() {
        return isAnswer;
    }

    public void setIsAnswer(Byte isAnswer) {
        this.isAnswer = isAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerOption that = (AnswerOption) o;
        return Objects.equals(id, that.id) && Objects.equals(option, that.option) && Objects.equals(isAnswer, that.isAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, option, isAnswer);
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
