package com.esliceu.parser.model.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "CURS")
@XmlSeeAlso({Group.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class Course {
    @XmlElement(name="GRUP")
    private List<Group> Groups;

    public List<Group> getGroups() {
        return Groups;
    }

    public void setGroups(List<Group> groups) {
        Groups = groups;
    }
}
