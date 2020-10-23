package com.example.retrofitconnection.config;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.retrofitconnection.dao.DepartamentoDAO;
import com.example.retrofitconnection.dao.ProfessorDAO;
import com.example.retrofitconnection.model.Departamento;
import com.example.retrofitconnection.model.Professor;

@Database(entities = {Professor.class, Departamento.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class RoomConfig extends RoomDatabase {

    private static RoomConfig instance = null;

    public abstract ProfessorDAO professorDAO();

    public abstract DepartamentoDAO departamentoDAO();

    public static RoomConfig getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context, RoomConfig.class, "databaseName").allowMainThreadQueries().build();
        }
        return instance;
    }
}
