package com.esliceu.parser.model.database;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grupo")
public class Group {

    public Group() {}

    public Group(Integer code){
        this.code = code;
    }

    @Id
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
