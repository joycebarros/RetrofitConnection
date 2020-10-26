package com.example.retrofitconnection.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Departamento {

  //  @PrimaryKey(autoGenerate = true)
  //  private int local_id;
  //  @ColumnInfo(name = "server_id")
    @PrimaryKey
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

//    public int getLocal_id() {
//        return local_id;
//    }
//
//    public void setLocal_id(int local_id) {
//        this.local_id = local_id;
//    }

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
}
