package com.example.retrofitconnection.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.retrofitconnection.model.Departamento;

import java.util.List;

@Dao
public interface DepartamentoDAO {

    @Query("SELECT * FROM Departamento")
    public List<Departamento> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    public void insertAll(List<Departamento> departamentos);

}
