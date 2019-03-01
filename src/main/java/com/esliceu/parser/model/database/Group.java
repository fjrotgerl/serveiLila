package com.esliceu.parser.model.database;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "grupo")
public class Group {

    @Id
    private Integer code;

    private String name;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "course_code")
    private Course course;


    public Group() {}

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {this.code = code;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    
}