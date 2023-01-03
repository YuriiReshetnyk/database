package com.reshetnyk.backend.domain;

import javax.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "user_name")
    private String userName;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "middle_name")
    private String middleName;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "phone_number")
    private String phoneNumber;
    @Basic
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "user")
    private List<UserProgress> userProgresses;

    @OneToMany(mappedBy = "user")
    private List<ExtraTable> extraTables;
    @ManyToMany
    @JoinTable(name = "user_course", catalog = "", schema = "reshetnyk", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false))
    private Set<Course> courses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return Objects.equals(id, that.id) && Objects.equals(userName, that.userName) && Objects.equals(firstName, that.firstName) && Objects.equals(middleName, that.middleName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, firstName, middleName, lastName, email, phoneNumber, password);
    }

    public List<UserProgress> getUserProgresses() {
        return userProgresses;
    }

    public void setUserProgresses(List<UserProgress> userProgresses) {
        this.userProgresses = userProgresses;
    }

    public List<ExtraTable> getExtraTables() {
        return extraTables;
    }

    public void setExtraTables(List<ExtraTable> extraTables) {
        this.extraTables = extraTables;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
