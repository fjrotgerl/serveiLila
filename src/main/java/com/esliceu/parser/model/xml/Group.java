package com.esliceu.parser.model.xml;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GRUP")
@XmlAccessorType(XmlAccessType.FIELD)
public class Group {

    @XmlAttribute(name="codi")
    private Integer code;

    @XmlAttribute(name="nom")
    private String name;

    @XmlAttribute(name="tutor")
    private String tutor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }
}
