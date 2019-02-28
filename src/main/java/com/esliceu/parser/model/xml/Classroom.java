package com.esliceu.parser.model.xml;


import javax.xml.bind.annotation.*;


@XmlRootElement(name = "AULA")
@XmlAccessorType(XmlAccessType.FIELD)
public class Classroom {

    @XmlAttribute(name="codi")
    private Integer code;

    @XmlAttribute(name="descripcio")
    private String description;

    public Integer getCodi() {
        return code;
    }

    public void setCodi(Integer codi) {
        this.code = codi;
    }

    public String getDescripcio() {
        return description;
    }

    public void setDescripcio(String descripcio) {
        this.description = descripcio;
    }
}
