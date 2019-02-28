package com.esliceu.parser.model.database;

import javax.persistence.*;

@Entity
@Table(name = "grupo")
public class Course {

    @Id
    private Integer code;

    private String name;

    private String group;


    public Course() {}

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    public String getGroup() {return group; }

    public void setGroup(String group) {  this.group = group;  }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

}