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
    private Integer day;
    @XmlAttribute(name="hora")
    private String hour;
    @XmlAttribute(name = "durada")
    private Integer durada;
    @XmlAttribute(name = "curs")
    private Integer curs;
    @XmlAttribute(name = "submateria")
    private Integer submateria;




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

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Integer getDurada() {return durada;}

    public void setDurada(Integer durada) {this.durada = durada;}

    public Integer getCurs() {return curs;}

    public void setCurs(Integer curs) { this.curs = curs;}

    public Integer getSubmateria() { return submateria;}

    public void setSubmateria(Integer submateria) { this.submateria = submateria;}
}
