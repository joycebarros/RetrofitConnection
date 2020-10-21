package com.example.retrofitconnection.model;

public class Professor {

    private int id;
    private String name;
    private String cpf;
    Departamento departament;

    public Professor() {
    }

    public Professor(String name, String cpf, Departamento departament) {
        this.name = name;
        this.cpf = cpf;
        this.departament = departament;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Departamento getDepartament() {
        return departament;
    }

    public void setDepartament(Departamento departament) {
        this.departament = departament;
    }

}
