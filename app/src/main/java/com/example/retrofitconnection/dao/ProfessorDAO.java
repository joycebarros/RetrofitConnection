package com.example.retrofitconnection.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.retrofitconnection.model.Professor;

import java.util.List;

@Dao
public interface ProfessorDAO {

    @Query("SELECT * FROM Professor")
    public List<Professor> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertALl(List<Professor> professors);


}
