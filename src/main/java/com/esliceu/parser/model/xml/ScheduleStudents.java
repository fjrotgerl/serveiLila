package com.esliceu.parser.model.xml;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name="HORARIA")
@XmlSeeAlso({StudentSession.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class ScheduleStudents {
    @XmlElement(name="SESSIO")
    private List<StudentSession> StudentSessions;

    public List<StudentSession> getStudentSessions() {
        return StudentSessions;
    }

    public void setStudentSessions(List<StudentSession> studentSessions) {
        StudentSessions = studentSessions;
    }
}
