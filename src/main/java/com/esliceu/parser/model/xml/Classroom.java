package com.esliceu.parser.model.xml;


import javax.xml.bind.annotation.*;
import java.util.List;


@XmlRootElement(name = "CENTRE_EXPORT")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ Teacher.class,Student.class, ScheduleStudents.class, ScheduleTeachers.class, Group.class })
public class Classroom {

    @XmlElement(name="PROFESSORS")
    private List<Teachers> professors;

    @XmlElement(name="ALUMNES")
    private List<Students> alumnes;

    @XmlElement(name="HORARIP")
    private List<ScheduleTeachers> scheduleTeachers;

    @XmlElement(name="HORARIA")
    private List<ScheduleStudents> scheduleStudents;

    @XmlElement(name="CURSOS")
    private List<Courses> courses;




    public List<ScheduleTeachers> getScheduleTeachers() {
        return scheduleTeachers;
    }

    public void setScheduleTeachers(List<ScheduleTeachers> scheduleTeachers) {
        this.scheduleTeachers = scheduleTeachers;
    }


    public List<ScheduleStudents> getScheduleStudents() {
        return scheduleStudents;
    }

    public void setScheduleStudents(List<ScheduleStudents> scheduleStudents) {
        this.scheduleStudents = scheduleStudents;
    }

    public List<Students> getAlumnes() {
        return alumnes;
    }

    public void setAlumnes(List<Students> alumnes) {
        this.alumnes = alumnes;
    }

    public List<Courses> getCourses() {
        return courses;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }

    public List<Teachers> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Teachers> professors) {
        this.professors = professors;
    }


    //HORARIA
}
