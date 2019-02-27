package com.esliceu.parser.model.database;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "grupo")
public class Group {

    @Id
    private Integer code;

    @OneToOne(mappedBy = "group")
    private Professor tutor;

    public Group() {}

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Professor getTutor() {return tutor;}

    public void setTutor(Professor tutor) {this.tutor = tutor;}

    public void setTutor(Optional<Professor> byId) {
    }
}