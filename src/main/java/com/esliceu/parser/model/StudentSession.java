package com.esliceu.parser.model;

import javax.persistence.*;

@Entity
public class StudentSession {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "studentCode")
    private Student student;
    private int day;
    private String hour;

    public StudentSession() {}

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
