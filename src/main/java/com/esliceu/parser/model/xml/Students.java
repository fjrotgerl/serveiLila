package com.esliceu.parser.model.xml;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name="ALUMNES")
@XmlSeeAlso({Student.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class Students {
    @XmlElement(name="ALUMNE")
    private List<Student> alumnes;


    public List<Student> getAlumnes() {
        return alumnes;
    }

    public void setAlumnes(List<Student> alumnes) {
        this.alumnes = alumnes;
    }
}
