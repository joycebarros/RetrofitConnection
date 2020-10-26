package com.example.retrofitconnection.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.retrofitconnection.model.Alocacao;

import java.util.List;

@Dao
public interface AlocacaoDAO {

    @Query("SELECT * FROM Alocacao")
    public List<Alocacao> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(List<Alocacao> alocacaos);
}
