package com.esliceu.parser.model.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name="CURSOS")
@XmlSeeAlso({Course.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class Courses {
    @XmlElement(name="CURS")
    private List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}