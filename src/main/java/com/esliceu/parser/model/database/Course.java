package com.esliceu.parser.model.database;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Course {

    @Id
    private Integer code;

    private String description;

    public Course(){
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
