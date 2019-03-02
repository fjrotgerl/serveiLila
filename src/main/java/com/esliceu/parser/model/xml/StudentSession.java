package com.esliceu.parser.model.xml;


import javax.xml.bind.annotation.*;

@XmlRootElement(name = "SESSIO")
@XmlAccessorType(XmlAccessType.FIELD)

public class StudentSession {

    //xml: ScheduleStudents Session

    @XmlAttribute(name="alumne")
    private String StudentCode;
    @XmlAttribute(name="dia")
    private Integer day;
    @XmlAttribute(name="hora")
    private String hour;
    @XmlAttribute(name = "submateria")
    private Integer submateria;
    @XmlAttribute(name = "durada")
    private Integer durada;


    public String getStudentCode() {
        return StudentCode;
    }

    public void setStudentCode(String studentCode) {
        StudentCode = studentCode;
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

    public Integer getSubmateria() {
        return submateria;
    }

    public void setSubmateria(Integer submateria) {
        this.submateria = submateria;
    }

    public Integer getDurada() {return durada;}

    public void setDurada(Integer durada) {this.durada = durada;}
}
