package com.esliceu.parser.model.database;

import javax.persistence.*;

@Entity
public class Student {

    @Id
    @Column(length = 50)
    private String code;

    private String name;
    private String firstSurname;
    private String secondSurname;

    @ManyToOne
    @JoinColumn(name = "groupCode")
    private Course course;

    public Student() {}


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}