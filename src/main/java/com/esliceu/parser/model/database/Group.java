package com.esliceu.parser.model.database;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "grupo")
public class Group {

    @Id
    private Integer code;


    public Group() {}

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }



}