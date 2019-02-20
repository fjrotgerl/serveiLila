package com.esliceu.parser.model;

public class StudentSession {

    private String StudentCode;
    private int day;
    private String hour;


    public String getStudentCode() {
        return StudentCode;
    }

    public void setStudentCode(String studentCode) {
        StudentCode = studentCode;
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
