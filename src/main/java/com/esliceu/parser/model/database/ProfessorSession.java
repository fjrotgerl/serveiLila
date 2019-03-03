package com.esliceu.parser.model.database;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

@Entity
public class ProfessorSession {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "grupo_code")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "subject_code")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "professor_code")
    private Professor professor;

    private String day;
    private String startHour;
    private String endHour;
    private Integer durada;





    public ProfessorSession() {}

    public String getDay() {
        return day;
    }

    public void setDay(Integer day) {
        Locale spanishLocale = new Locale("es", "ES");
        String dayName = DayOfWeek.of(day).getDisplayName(TextStyle.FULL,spanishLocale);
        this.day = dayName;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }


    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getDurada() {
        return durada;
    }

    public void setDurada(Integer durada) {
        this.durada = durada;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }
}