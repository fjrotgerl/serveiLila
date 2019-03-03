package com.esliceu.parser.model.database;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Professor {

    @Id
    @Column(length = 50)
    private String code;

    private String name;
    private String firstSurname;
    private String secondSurname;

    @OneToOne
    private Group group;

    @OneToMany(mappedBy = "professor")
    private Set<ProfessorSession> professorSessions;



    public Professor() {}

    public String getCodi() {
        return code;
    }

    public void setCodi(String code) {
        this.code = code;
    }

    public String getNom() {
        return name;
    }

    public void setNom(String name) {
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

    public String getCode() {return code;}

    public void setCode(String code) {this.code = code; }

    public String getName() {return name;}

    public void setName(String name) {this.name = name; }

    public Set<ProfessorSession> getProfessorSessions() {return professorSessions; }

    public void setProfessorSessions(Set<ProfessorSession> professorSessions) {this.professorSessions = professorSessions;}
}