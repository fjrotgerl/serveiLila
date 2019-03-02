package com.esliceu.parser.model.database;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

@Entity
public class StudentSession {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Subject subject;

    private String day;
    private String StartHour;
    private Integer durada;
    private String endHour;

    public StudentSession() {}

    public String getDay() {
        return day;
    }

    public void setDay(int day) {
        Locale spanishLocale = new Locale("es", "ES");
        String dayName = DayOfWeek.of(day).getDisplayName(TextStyle.FULL,spanishLocale);
        this.day = dayName;
    }

    public String getStartHour() {
        return StartHour;
    }

    public void setStartHour(String startHour) {
        this.StartHour = startHour;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setDay(String day) {this.day = day;}

    public Integer getDurada() {return durada;}

    public void setDurada(Integer durada) {this.durada = durada;}

    public String getEndHour() {return endHour;}

    public void setEndHour(String endHour) {this.endHour = endHour;}
}

