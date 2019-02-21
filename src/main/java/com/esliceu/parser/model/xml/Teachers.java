package com.esliceu.parser.model.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name="PROFESSORS")
@XmlSeeAlso({Teacher.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class Teachers {

    @XmlElement(name="PROFESSOR")
    private List<Teacher> teacher;


    public List<Teacher> getTeacher() {
        return teacher;
    }

    public void setTeacher(List<Teacher> teacher) {
        this.teacher = teacher;
    }
}
