package com.esliceu.parser.model.xml;


import javax.xml.bind.annotation.*;


@XmlRootElement(name = "SUBMATERIA")
@XmlSeeAlso({Group.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class Subject {

    @XmlElement(name="codi")
    private Integer codi;

    @XmlElement(name="curs")
    private String curs;

    @XmlElement(name = "descripcio")
    private String descripcio;

    public Integer getCodi() {
        return codi;
    }

    public void setCodi(Integer codi) {
        this.codi = codi;
    }

    public String getCurs() {
        return curs;
    }

    public void setCurs(String curs) {
        this.curs = curs;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }
}
