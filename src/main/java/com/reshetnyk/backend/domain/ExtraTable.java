package com.reshetnyk.backend.domain;

import javax.persistence.*;
import java.util.Objects;
import com.reshetnyk.backend.domain.User;

@Entity
@Table(name = "extra_table", schema = "reshetnyk", catalog = "")
public class ExtraTable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "string_colum")
    private String stringColum;
    @Basic
    @Column(name = "int_colum")
    private Integer intColum;
    @ManyToOne
    @JoinColumn(name = "foreign_user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStringColum() {
        return stringColum;
    }

    public void setStringColum(String stringColum) {
        this.stringColum = stringColum;
    }

    public Integer getIntColum() {
        return intColum;
    }

    public void setIntColum(Integer intColum) {
        this.intColum = intColum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExtraTable that = (ExtraTable) o;
        return id == that.id && Objects.equals(stringColum, that.stringColum) && Objects.equals(intColum, that.intColum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stringColum, intColum);
    }
}
