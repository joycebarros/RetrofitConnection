package com.example.retrofitconnection.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.retrofitconnection.model.Curso;


import java.util.List;

@Dao
public interface CursoDAO {

    @Query("SELECT * FROM Curso")
    public List<Curso> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(List<Curso> cursos);
}
