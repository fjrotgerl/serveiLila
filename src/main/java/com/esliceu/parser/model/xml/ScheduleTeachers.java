package com.esliceu.parser.model.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name="HORARIP")
@XmlSeeAlso({TeachersSession.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class ScheduleTeachers {
    @XmlElement(name="SESSIO")
    private List<TeachersSession> TeachersSessions;

    public List<TeachersSession> getTeachersSessions() {
        return TeachersSessions;
    }

    public void setTeachersSessions(List<TeachersSession> TeachersSessions) {
        this.TeachersSessions = TeachersSessions;
    }
}
