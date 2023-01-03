package com.reshetnyk.backend.domain;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class UserProgress {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "begin_timestamp")
    private Timestamp beginTimestamp;
    @Basic
    @Column(name = "end_timestamp")
    private Timestamp endTimestamp;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id", nullable = false)
    private Test test;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getBeginTimestamp() {
        return beginTimestamp;
    }

    public void setBeginTimestamp(Timestamp beginTimestamp) {
        this.beginTimestamp = beginTimestamp;
    }

    public Timestamp getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Timestamp endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProgress that = (UserProgress) o;
        return Objects.equals(id, that.id) && Objects.equals(beginTimestamp, that.beginTimestamp) && Objects.equals(endTimestamp, that.endTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, beginTimestamp, endTimestamp);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
