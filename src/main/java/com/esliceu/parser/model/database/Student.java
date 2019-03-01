package com.esliceu.parser.model.database;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Student {

    @Id
    @Column(length = 50)
    private String code;

    private String name;
    private String firstSurname;
    private String secondSurname;

    @ManyToOne
    @JoinColumn(name = "grupo_code")
    private Group group;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<StudentSession> studentSessions;

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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Set<StudentSession> getStudentSessions() {
        return studentSessions;
    }

    public void setStudentSessions(Set<StudentSession> studentSessions) {
        this.studentSessions = studentSessions;
    }
}