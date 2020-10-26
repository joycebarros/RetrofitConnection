package com.example.retrofitconnection.config;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.retrofitconnection.dao.AlocacaoDAO;
import com.example.retrofitconnection.dao.CursoDAO;
import com.example.retrofitconnection.dao.DepartamentoDAO;
import com.example.retrofitconnection.dao.ProfessorDAO;
import com.example.retrofitconnection.model.Alocacao;
import com.example.retrofitconnection.model.Curso;
import com.example.retrofitconnection.model.Departamento;
import com.example.retrofitconnection.model.Professor;

@Database(entities = {Professor.class, Departamento.class, Curso.class, Alocacao.class}, version = 1)
@TypeConverters({Converters.class, Converters2.class})
public abstract class RoomConfig extends RoomDatabase {

    private static RoomConfig instance = null;

    public abstract ProfessorDAO professorDAO();

    public abstract DepartamentoDAO departamentoDAO();

    public abstract CursoDAO cursoDAO();

    public abstract AlocacaoDAO alocacaoDAO();

    public static RoomConfig getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context, RoomConfig.class, "databaseName").allowMainThreadQueries().build();
        }
        return instance;
    }
}
