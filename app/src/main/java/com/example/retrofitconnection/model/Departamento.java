package com.example.retrofitconnection.model;

public class Departamento {

    private int id;
    private String name;

    public Departamento(){

    }

    public Departamento(String name) {
        this.name = name;
    }

    public Departamento(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
