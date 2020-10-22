package com.example.retrofitconnection.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Professor {
    @PrimaryKey(autoGenerate = true)
    private int local_id;
    @ColumnInfo(name = "server_id")
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

    public int getLocal_id() {
        return local_id;
    }

    public void setLocal_id(int local_id) {
        this.local_id = local_id;
    }

    public void setId(int id) {
        this.id = id;
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
