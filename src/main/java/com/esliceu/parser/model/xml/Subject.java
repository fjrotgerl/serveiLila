package com.esliceu.parser.model.xml;


import javax.xml.bind.annotation.*;


@XmlRootElement(name = "SUBMATERIA")
@XmlSeeAlso({Group.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class Subject {

    @XmlAttribute(name="codi")
    private Integer codi;

    @XmlAttribute(name="curs")
    private Integer curs;

    @XmlAttribute(name = "descripcio")
    private String descripcio;

    public Integer getCodi() {
        return codi;
    }

    public void setCodi(Integer codi) {
        this.codi = codi;
    }

    public Integer getCurs() {
        return curs;
    }

    public void setCurs(Integer curs) {
        this.curs = curs;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }
}
