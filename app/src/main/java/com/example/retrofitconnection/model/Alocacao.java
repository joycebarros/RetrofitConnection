package com.example.retrofitconnection.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Alocacao {

    @PrimaryKey
    private int id;
    private String dayOfWeek;
    private int startHour;
    private int endHour;
    Professor professor;
    Curso course;

    public Alocacao() {
    }

    public Alocacao(String dayOfWeek, int startHour, int endHour, Professor professor, Curso curso) {
        this.dayOfWeek = dayOfWeek;
        this.startHour = startHour;
        this.endHour = endHour;
        this.professor = professor;
        this.course = curso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Curso getCourse() {
        return course;
    }

    public void setCourse(Curso course) {
        this.course = course;
    }
}
