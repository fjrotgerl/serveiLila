package com.esliceu.parser.model.xml;


import javax.xml.bind.annotation.*;

@XmlRootElement(name = "SESSIO")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeachersSession {

    //xml: ScheduleTeachers > Session

    @XmlAttribute(name="professor")
    private String professorCode;
    @XmlAttribute(name="grup")
    private Integer groupCode;
    @XmlAttribute(name="dia")
    private int day;
    @XmlAttribute(name="hora")
    private String hour;


    public String getProfessorCode() {
        return professorCode;
    }

    public void setProfessorCode(String professorCode) {
        this.professorCode = professorCode;
    }

    public Integer getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(Integer groupCode) {
        this.groupCode = groupCode;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

}
