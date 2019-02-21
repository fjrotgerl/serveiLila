package com.esliceu.parser.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Group {

    public Group(String code){
        this.code = code;
    }

    @Id
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
