package com.esliceu.parser.model.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "CURS")
@XmlSeeAlso({Group.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class Course {

    @XmlAttribute(name = "codi")
    private Integer codi;

    @XmlAttribute(name = "descripcio")
    private String descripcio;

    @XmlElement(name="GRUP")
    private List<Group> Groups;


    public Integer getCodi() {
        return codi;
    }

    public void setCodi(Integer codi) {
        this.codi = codi;
    }

    public List<Group> getGroups() {
        return Groups;
    }

    public void setGroups(List<Group> groups) {
        Groups = groups;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }
}
